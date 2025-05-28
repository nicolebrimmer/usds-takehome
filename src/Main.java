import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        try {
            /* Get all government agencies.*/
            String agencyHttpResponse = getHttpResponse("https://www.ecfr.gov/api/admin/v1/agencies.json");
            ObjectMapper mapper = new ObjectMapper();
            AgencyList agencyList = mapper.readValue(agencyHttpResponse, AgencyList.class);
            for (Agency agency : agencyList.agencies) {
                System.out.println(agency.getName());
                calculateWordCount(agency);
                System.out.println(agency.getWordCount());
                System.out.println();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static String getHttpResponse(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        int status = conn.getResponseCode();

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        in.close();
        conn.disconnect();

        return content.toString();
    }

    private static long calculateWordCount(Agency agency) throws IOException {
        if (agency.getWordCount() >= 0) {
            return agency.getWordCount();
        }

        long totalWordCount = 0;
        for (Agency child: agency.getChildren()) {
            long agencyWordCount = calculateWordCount(child);
            totalWordCount += agencyWordCount;

        }

        for (CfrReference cfrReference: agency.getCfrReferences()) {
            totalWordCount += getWordCount(cfrReference);
        }

        agency.setWordCount(totalWordCount);
        return totalWordCount;
    }

    private static long getWordCount(CfrReference cfrReference) throws IOException {
        /* Fetch the issue date of the latest version. */
        // Build URL.
        StringBuilder urlParameters = new StringBuilder();
        boolean addedUrlParameter = false;
        if (!cfrReference.getChapter().isEmpty()) {
            urlParameters.append("chapter=").append(cfrReference.getChapter());
            addedUrlParameter = true;
        }
        if (!cfrReference.getSubtitle().isEmpty()) {
            if (addedUrlParameter) {
                urlParameters.append("&");
            }
            urlParameters.append("subtitle=").append(cfrReference.getSubtitle());
            addedUrlParameter = true;
        }
        if (!cfrReference.getPart().isEmpty()) {
            if (addedUrlParameter) {
                urlParameters.append("&");
            }
            urlParameters.append("part=").append(cfrReference.getPart());
            addedUrlParameter = true;
        }
        if (!cfrReference.getSubchapter().isEmpty()) {
            if (addedUrlParameter) {
                urlParameters.append("&");
            }
            urlParameters.append("subchapter=").append(cfrReference.getSubchapter());
            addedUrlParameter = true;
        }
        String getCfrVersionInfoUrlStr = String.format("https://www.ecfr.gov/api/versioner/v1/versions/title-%s.json%s", cfrReference.getTitle(), urlParameters);
        String cfrVersionInfoHttpResponse = getHttpResponse(getCfrVersionInfoUrlStr);
        ObjectMapper objectMapper = new ObjectMapper();
        CfrVersionInfo cfrVersionInfo = objectMapper.readValue(cfrVersionInfoHttpResponse, CfrVersionInfo.class);
        Date latestIssueDate = cfrVersionInfo.getMeta().getLatestIssueDate();

        /* Fetch most recent regulation. */
        LocalDate localLatestIssueDate = latestIssueDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        String formattedLatestIssueDate = localLatestIssueDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
        String getMostRecentVersionUrl = String.format("https://www.ecfr.gov/api/versioner/v1/full/%s/title-%s.xml?%s", formattedLatestIssueDate, cfrReference.getTitle(), urlParameters);

        String mostRecentVersionHttpResponse = getHttpResponse(getMostRecentVersionUrl);
        XmlMapper xmlMapper = new XmlMapper();
        Object tree = xmlMapper.readTree(mostRecentVersionHttpResponse);
        String plainText = tree.toString();

        // It would be much better to run plainText.trim().split(' ').length but
        // that creates a massive array which can cause issues with the heap.
        // Note that I am assuming that there is only one space between sentences (which
        // I spot checked it to be so).
        return plainText.chars().filter(ch -> ch == ' ').count();
    }
}
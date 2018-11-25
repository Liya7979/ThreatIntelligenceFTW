package com.threatingintelligenceftw.Database;

import java.util.ArrayList;
import java.util.List;

import org.json.*;

public class JSONToCVE {
    private List<NVDCVE> cveList;

    public JSONToCVE(String json) throws JSONException {
        cveList = new ArrayList<>();
        JSONObject object = new JSONObject(json);
        JSONArray cveArray = object.getJSONArray("CVE_Items");
        for (int i = 0; i < cveArray.length(); i++) {
            parseCVE(cveArray.getJSONObject(i));
        }
    }

    public List<NVDCVE> getCveList() {
        return cveList;
    }

    private void parseCVE(JSONObject arrayElem) {
        try {
            JSONObject jsonCVE = arrayElem.getJSONObject("cve");
            NVDCVE cve = new NVDCVE();
            cve.setId(jsonCVE.getJSONObject("CVE_data_meta").getString("ID"));
            JSONArray jsonArray = jsonCVE.
                    getJSONObject("affects").
                    getJSONObject("vendor").
                    getJSONArray("vendor_data");
            if (jsonArray.length() != 0) {
                JSONObject jsonVendor = jsonArray.getJSONObject(0);
                cve.setVendor(parseVendor(jsonVendor));
            }
            JSONArray problemDescr = jsonCVE.
                    getJSONObject("problemtype").
                    getJSONArray("problemtype_data").
                    getJSONObject(0).getJSONArray("description");
            if (problemDescr.length() != 0) {
                cve.setCWE(problemDescr.getJSONObject(0).getString("value"));
            }
            cve.setDescription(jsonCVE.
                    getJSONObject("description").
                    getJSONArray("description_data").
                    getJSONObject(0).
                    getString("value"));
            cve.setRefs(parseReferences(jsonCVE.
                    getJSONObject("references").
                    getJSONArray("reference_data")));

            if (arrayElem.has("impact")) {
                JSONObject metrics = arrayElem.getJSONObject("impact");
                cve.setMetricsV3(parseMetricsV3(metrics));
                cve.setMetricsV2(parseMetricsV2(metrics));
            }
            cve.setPublishedDate(arrayElem.getString("publishedDate"));
            cve.setLastModifiedDate(arrayElem.getString("lastModifiedDate"));

            cveList.add(cve);
        } catch (JSONException e) {
            System.err.println("Couldn't parse a json unit: " + e.getMessage());
        }
    }

    private Vendor parseVendor(JSONObject jsonVendor) {
        try {
            String vendorName = jsonVendor.getString("vendor_name");
            JSONObject product = jsonVendor.
                    getJSONObject("product").
                    getJSONArray("product_data").
                    getJSONObject(0);
            String productName = product.getString("product_name");
            JSONArray jsonVersions = product.
                    getJSONObject("version").
                    getJSONArray("version_data");
            List<String> versions = new ArrayList<>();
            for (int i = 0; i < jsonVersions.length(); i++) {
                versions.add(jsonVersions.getJSONObject(i).getString("version_value"));
            }
            return new Vendor(vendorName, productName, versions);
        } catch (JSONException e) {
            System.err.println("Couldn't parse a vendor: " + e.getMessage());
        }
        return null;
    }

    private List<Reference> parseReferences(JSONArray jsonRefs) throws JSONException {
        List<Reference> refList = new ArrayList<>();
        for (int i = 0; i < jsonRefs.length(); i++) {
            JSONObject ref = jsonRefs.getJSONObject(i);
            refList.add(new Reference(ref.getString("url"), ref.getString("name")));
        }
        return refList;
    }

    private MetricsV3 parseMetricsV3(JSONObject jsonImpact) {
        if (jsonImpact.has("baseMetricV3")) {

            MetricsV3 metricsV3 = new MetricsV3();
            try {
                JSONObject jsonMetricsV3 = jsonImpact.getJSONObject("baseMetricV3");

                JSONObject jsonCVSSV3 = jsonMetricsV3.getJSONObject("cvssV3");
                CVSSV3 cvssv3 = new CVSSV3();
                cvssv3.setVersion(jsonCVSSV3.getString("version"));
                cvssv3.setAttackVector(jsonCVSSV3.getString("attackVector"));
                cvssv3.setAttackComplexity(jsonCVSSV3.getString("attackComplexity"));
                cvssv3.setPrivilegesRequired(jsonCVSSV3.getString("privilegesRequired"));
                cvssv3.setUserInteraction(jsonCVSSV3.getString("userInteraction"));
                cvssv3.setScope(jsonCVSSV3.getString("scope"));
                cvssv3.setConfidentialityImpact(jsonCVSSV3.getString("confidentialityImpact"));
                cvssv3.setIntegrityImpact(jsonCVSSV3.getString("integrityImpact"));
                cvssv3.setAvailabilityImpact(jsonCVSSV3.getString("availabilityImpact"));
                cvssv3.setBaseScore(jsonCVSSV3.getDouble("baseScore"));
                cvssv3.setBaseSeverity(jsonCVSSV3.getString("baseSeverity"));

                metricsV3.setCvss(cvssv3);
                metricsV3.setExploitabilityScore(jsonMetricsV3.getDouble("exploitabilityScore"));
                metricsV3.setImpactScore(jsonMetricsV3.getDouble("impactScore"));

                return metricsV3;
            } catch (JSONException e) {
                System.err.println("Couldn't parse V3 metrics: " + e.getMessage());
            }
        }
        return null;
    }

    private MetricsV2 parseMetricsV2(JSONObject jsonImpact) {
        if (jsonImpact.has("baseMetricV2")) {

            MetricsV2 metricsV2 = new MetricsV2();
            try {
                JSONObject jsonMetricsV2 = jsonImpact.getJSONObject("baseMetricV2");

                JSONObject jsonCVSSV2 = jsonMetricsV2.getJSONObject("cvssV2");
                CVSSV2 cvssv2 = new CVSSV2();
                cvssv2.setAccessVector(jsonCVSSV2.getString("accessVector"));
                cvssv2.setAccessComplexity(jsonCVSSV2.getString("accessComplexity"));
                cvssv2.setAuthentication(jsonCVSSV2.getString("authentication"));
                cvssv2.setConfidentialityImpact(jsonCVSSV2.getString("confidentialityImpact"));
                cvssv2.setIntegrityImpact(jsonCVSSV2.getString("integrityImpact"));
                cvssv2.setAvailabilityImpact(jsonCVSSV2.getString("availabilityImpact"));
                cvssv2.setBaseScore(jsonCVSSV2.getDouble("baseScore"));
                metricsV2.setCvss(cvssv2);
                metricsV2.setSeverity(jsonMetricsV2.getString("severity"));
                metricsV2.setExploitabilityScore(jsonMetricsV2.getDouble("exploitabilityScore"));
                metricsV2.setImpactScore(jsonMetricsV2.getDouble("impactScore"));
                metricsV2.setObtainAllPrivilege(jsonMetricsV2.getBoolean("obtainAllPrivilege"));
                metricsV2.setObtainUserPrivilege(jsonMetricsV2.getBoolean("obtainUserPrivilege"));
                metricsV2.setObtainOtherPrivilege(jsonMetricsV2.getBoolean("obtainOtherPrivilege"));
                metricsV2.setUserInteractionRequired(jsonMetricsV2.getBoolean("userInteractionRequired"));

                return metricsV2;
            } catch (JSONException e) {
                System.err.println("Couldn't parse V2 metrics: " + e.getMessage());
            }
        }
        return null;
    }
}

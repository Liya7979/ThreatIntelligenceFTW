package com.threatingintelligenceftw.Database;

import java.io.Serializable;
import java.util.List;

public class NVDCVE implements Serializable {

    private String CVE;
    private String authoritySource = "NVD";
    private String id;
    private Vendor vendor;
    private String CWE;
    private List<Reference> refs;
    private String description;
    private MetricsV3 metricsV3;
    private MetricsV2 metricsV2;
    private String publishedDate;
    private String lastModifiedDate;

    public void setCVE(String CVE) {
        this.CVE = CVE;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public void setCWE(String CWE) {
        this.CWE = CWE;
    }

    public void setRefs(List<Reference> refs) {
        this.refs = refs;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMetricsV3(MetricsV3 metricsV3) {
        this.metricsV3 = metricsV3;
    }

    public void setMetricsV2(MetricsV2 metricsV2) {
        this.metricsV2 = metricsV2;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public void setLastModifiedDate(String lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}

class Vendor implements Serializable {
    String vendorName;
    String productName;
    List<String> versions;

    public Vendor(String vendorName, String productName, List<String> versions) {
        this.vendorName = vendorName;
        this.productName = productName;
        this.versions = versions;
    }
}

class Reference implements Serializable {
    private String url;
    private String name;

    Reference(String url, String name) {
        this.url = url;
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class MetricsV3 implements Serializable {
    private CVSSV3 cvss;
    private double exploitabilityScore;
    private double impactScore;

    public CVSSV3 getCvss() {
        return cvss;
    }

    public double getExploitabilityScore() {
        return exploitabilityScore;
    }

    public double getImpactScore() {
        return impactScore;
    }

    public void setCvss(CVSSV3 cvss) {
        this.cvss = cvss;
    }

    public void setExploitabilityScore(double exploitabilityScore) {
        this.exploitabilityScore = exploitabilityScore;
    }

    public void setImpactScore(double impactScore) {
        this.impactScore = impactScore;
    }
}

class CVSSV3 implements Serializable {
    private String version;
    private String attackVector;
    private String attackComplexity;
    private String privilegesRequired;
    private String userInteraction;
    private String scope;
    private String confidentialityImpact;
    private String integrityImpact;
    private String availabilityImpact;
    private double baseScore;
    private String baseSeverity;

    public String getVersion() {
        return version;
    }

    public String getAttackVector() {
        return attackVector;
    }

    public String getAttackComplexity() {
        return attackComplexity;
    }

    public String getPrivilegesRequired() {
        return privilegesRequired;
    }

    public String getUserInteraction() {
        return userInteraction;
    }

    public String getScope() {
        return scope;
    }

    public String getConfidentialityImpact() {
        return confidentialityImpact;
    }

    public String getIntegrityImpact() {
        return integrityImpact;
    }

    public String getAvailabilityImpact() {
        return availabilityImpact;
    }

    public double getBaseScore() {
        return baseScore;
    }

    public String getBaseSeverity() {
        return baseSeverity;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setAttackVector(String attackVector) {
        this.attackVector = attackVector;
    }

    public void setAttackComplexity(String attackComplexity) {
        this.attackComplexity = attackComplexity;
    }

    public void setPrivilegesRequired(String privilegesRequired) {
        this.privilegesRequired = privilegesRequired;
    }

    public void setUserInteraction(String userInteraction) {
        this.userInteraction = userInteraction;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public void setConfidentialityImpact(String confidentialityImpact) {
        this.confidentialityImpact = confidentialityImpact;
    }

    public void setIntegrityImpact(String integrityImpact) {
        this.integrityImpact = integrityImpact;
    }

    public void setAvailabilityImpact(String availabilityImpact) {
        this.availabilityImpact = availabilityImpact;
    }

    public void setBaseScore(double baseScore) {
        this.baseScore = baseScore;
    }

    public void setBaseSeverity(String baseSeverity) {
        this.baseSeverity = baseSeverity;
    }
}

class MetricsV2 implements Serializable {
    private CVSSV2 cvss;
    private String severity;
    private double exploitabilityScore;
    private double impactScore;
    private boolean obtainAllPrivilege;
    private boolean obtainUserPrivilege;
    private boolean obtainOtherPrivilege;
    private boolean userInteractionRequired;

    public CVSSV2 getCvss() {
        return cvss;
    }

    public String getSeverity() {
        return severity;
    }

    public double getExploitabilityScore() {
        return exploitabilityScore;
    }

    public double getImpactScore() {
        return impactScore;
    }

    public boolean isObtainAllPrivilege() {
        return obtainAllPrivilege;
    }

    public boolean isObtainUserPrivilege() {
        return obtainUserPrivilege;
    }

    public boolean isObtainOtherPrivilege() {
        return obtainOtherPrivilege;
    }

    public boolean isUserInteractionRequired() {
        return userInteractionRequired;
    }

    public void setCvss(CVSSV2 cvss) {
        this.cvss = cvss;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public void setExploitabilityScore(double exploitabilityScore) {
        this.exploitabilityScore = exploitabilityScore;
    }

    public void setImpactScore(double impactScore) {
        this.impactScore = impactScore;
    }

    public void setObtainAllPrivilege(boolean obtainAllPrivilege) {
        this.obtainAllPrivilege = obtainAllPrivilege;
    }

    public void setObtainUserPrivilege(boolean obtainUserPrivilege) {
        this.obtainUserPrivilege = obtainUserPrivilege;
    }

    public void setObtainOtherPrivilege(boolean obtainOtherPrivilege) {
        this.obtainOtherPrivilege = obtainOtherPrivilege;
    }

    public void setUserInteractionRequired(boolean userInteractionRequired) {
        this.userInteractionRequired = userInteractionRequired;
    }
}

class CVSSV2 implements Serializable {
    private String version;
    private String accessVector;
    private String accessComplexity;
    private String authentication;
    private String confidentialityImpact;
    private String integrityImpact;
    private String availabilityImpact;
    private double baseScore;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAccessVector() {
        return accessVector;
    }

    public void setAccessVector(String accessVector) {
        this.accessVector = accessVector;
    }

    public String getAccessComplexity() {
        return accessComplexity;
    }

    public void setAccessComplexity(String accessComplexity) {
        this.accessComplexity = accessComplexity;
    }

    public String getAuthentication() {
        return authentication;
    }

    public void setAuthentication(String authentication) {
        this.authentication = authentication;
    }

    public String getConfidentialityImpact() {
        return confidentialityImpact;
    }

    public void setConfidentialityImpact(String confidentialityImpact) {
        this.confidentialityImpact = confidentialityImpact;
    }

    public String getIntegrityImpact() {
        return integrityImpact;
    }

    public void setIntegrityImpact(String integrityImpact) {
        this.integrityImpact = integrityImpact;
    }

    public String getAvailabilityImpact() {
        return availabilityImpact;
    }

    public void setAvailabilityImpact(String availabilityImpact) {
        this.availabilityImpact = availabilityImpact;
    }

    public double getBaseScore() {
        return baseScore;
    }

    public void setBaseScore(double baseScore) {
        this.baseScore = baseScore;
    }
}
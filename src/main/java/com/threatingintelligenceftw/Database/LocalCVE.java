package com.threatingintelligenceftw.Database;

import java.io.Serializable;
import java.util.List;

public class LocalCVE implements Serializable {

    private String CVE;
    private String id;
    private Vendor vendor;
    private String CWE;
    private List<Reference> refs;
    private String description;
    private MetricsV3 metricsV3;
    private MetricsV2 metricsV2;
    private String publishedDate;
    private String lastModifiedDate;

    String getCVE() {
        return CVE;
    }

    public String getId() {
        return id;
    }

    Vendor getVendor() {
        return vendor;
    }

    String getCWE() {
        return CWE;
    }

    List<Reference> getRefs() {
        return refs;
    }

    String getDescription() {
        return description;
    }

    MetricsV3 getMetricsV3() {
        return metricsV3;
    }

    MetricsV2 getMetricsV2() {
        return metricsV2;
    }

    String getPublishedDate() {
        return publishedDate;
    }

    String getLastModifiedDate() {
        return lastModifiedDate;
    }

    void setId(String id) {
        this.id = id;
    }

    void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    void setCWE(String CWE) {
        this.CWE = CWE;
    }

    void setRefs(List<Reference> refs) {
        this.refs = refs;
    }

    void setDescription(String description) {
        this.description = description;
    }

    void setMetricsV3(MetricsV3 metricsV3) {
        this.metricsV3 = metricsV3;
    }

    void setMetricsV2(MetricsV2 metricsV2) {
        this.metricsV2 = metricsV2;
    }

    void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    void setLastModifiedDate(String lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}

class Vendor implements Serializable {
    private final String vendorName;
    private final String productName;
    private final List<String> versions;

    Vendor(String vendorName, String productName, List<String> versions) {
        this.vendorName = vendorName;
        this.productName = productName;
        this.versions = versions;
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Vendor:\t\t").append(vendorName).append('\n');
        builder.append("Product name:\t").append(productName).append('\n');
        builder.append("Versions:");
        for (String version : versions) {
            builder.append("\t\t").append(version).append('\n');
        }
        builder.append('\n');
        return builder.toString();
    }
}

class Reference implements Serializable {
    private final String url;
    private String name;

    Reference(String url, String name) {
        this.url = url;
        this.name = name;
    }
    
    @Override
    public String toString() {
        return "Name: " + name + "\n\t\tURL: " + url + '\n';
    }
}

class MetricsV3 implements Serializable {
    
    private CVSSV3 cvss;
    private double exploitabilityScore;
    private double impactScore;
    
    @Override
    public String toString() {
        return "\t\tCVSSV3:\n" +
                cvss.toString() +
                "\n\t\tExploitability score:\t" + exploitabilityScore +
                "\n\t\tImpact score:\t\t" + impactScore + "\n\n";
    }

    void setCvss(CVSSV3 cvss) {
        this.cvss = cvss;
    }

    void setExploitabilityScore(double exploitabilityScore) {
        this.exploitabilityScore = exploitabilityScore;
    }

    void setImpactScore(double impactScore) {
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
    
    @Override
    public String toString() {
        String ttt = "\t\t\t";

        return ttt + "Version:\t\t" + version + '\n' +
                ttt + "Attack vector:\t" + attackVector + '\n' +
                ttt + "Attack complexity:\t" + attackComplexity + '\n' +
                ttt + "Privileges required:\t" + privilegesRequired + '\n' +
                ttt + "User interaction:\t" + userInteraction + '\n' +
                ttt + "Scope:\t\t" + scope + '\n' +
                ttt + "Confidentiality impact:\t" + confidentialityImpact + '\n' +
                ttt + "Integrity impact:\t" + integrityImpact + '\n' +
                ttt + "Availability impact:\t" + availabilityImpact + '\n' +
                ttt + "Base score:\t\t" + baseScore + '\n' +
                ttt + "Base severity:\t" + baseSeverity + '\n';
    }

    public String getVersion() {
        return version;
    }

    public String getScope() {
        return scope;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    void setAttackVector(String attackVector) {
        this.attackVector = attackVector;
    }

    void setAttackComplexity(String attackComplexity) {
        this.attackComplexity = attackComplexity;
    }

    void setPrivilegesRequired(String privilegesRequired) {
        this.privilegesRequired = privilegesRequired;
    }

    void setUserInteraction(String userInteraction) {
        this.userInteraction = userInteraction;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    void setConfidentialityImpact(String confidentialityImpact) {
        this.confidentialityImpact = confidentialityImpact;
    }

    void setIntegrityImpact(String integrityImpact) {
        this.integrityImpact = integrityImpact;
    }

    void setAvailabilityImpact(String availabilityImpact) {
        this.availabilityImpact = availabilityImpact;
    }

    void setBaseScore(double baseScore) {
        this.baseScore = baseScore;
    }

    void setBaseSeverity(String baseSeverity) {
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
    
    @Override
    public String toString() {
        return "\t\tCVSSV2:\n" + cvss.toString() +
                "\n\t\tSerenity:\t\t" + severity +
                "\n\t\tExploitability score:\t" + exploitabilityScore +
                "\n\t\tImpact score:\t\t" + impactScore +
                "\n\t\tObtainAllPrivilege:\t" + (obtainAllPrivilege ? "TRUE" : "FALSE") +
                "\n\t\tObtainUserPrivilege:\t" + (obtainUserPrivilege ? "TRUE" : "FALSE") +
                "\n\t\tObtainOtherPrivilege:\t" + (obtainOtherPrivilege ? "TRUE" : "FALSE") +
                "\n\t\tUserIntegrationRequired:\t" + (userInteractionRequired ? "TRUE" : "FALSE") + "\n\n";
    }

    void setCvss(CVSSV2 cvss) {
        this.cvss = cvss;
    }

    void setSeverity(String severity) {
        this.severity = severity;
    }

    void setExploitabilityScore(double exploitabilityScore) {
        this.exploitabilityScore = exploitabilityScore;
    }

    void setImpactScore(double impactScore) {
        this.impactScore = impactScore;
    }

    void setObtainAllPrivilege(boolean obtainAllPrivilege) {
        this.obtainAllPrivilege = obtainAllPrivilege;
    }

    void setObtainUserPrivilege(boolean obtainUserPrivilege) {
        this.obtainUserPrivilege = obtainUserPrivilege;
    }

    void setObtainOtherPrivilege(boolean obtainOtherPrivilege) {
        this.obtainOtherPrivilege = obtainOtherPrivilege;
    }

    void setUserInteractionRequired(boolean userInteractionRequired) {
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
    
    @Override
    public String toString() {
        String ttt = "\t\t\t";
        char n = '\n';

        return ttt + "Version:\t\t" + version + n +
                ttt + "Access vector:\t" + accessVector + n +
                ttt + "Access complexity:\t" + accessComplexity + n +
                ttt + "Authentication:\t" + authentication + n +
                ttt + "Confidentiality impact:\t" + confidentialityImpact + n +
                ttt + "Integrity impact:\t" + integrityImpact + n +
                ttt + "Availability impact:\t" + availabilityImpact + n +
                ttt + "Base score:\t\t" + baseScore + n;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    void setAccessVector(String accessVector) {
        this.accessVector = accessVector;
    }

    void setAccessComplexity(String accessComplexity) {
        this.accessComplexity = accessComplexity;
    }

    void setAuthentication(String authentication) {
        this.authentication = authentication;
    }

    void setConfidentialityImpact(String confidentialityImpact) {
        this.confidentialityImpact = confidentialityImpact;
    }

    void setIntegrityImpact(String integrityImpact) {
        this.integrityImpact = integrityImpact;
    }

    void setAvailabilityImpact(String availabilityImpact) {
        this.availabilityImpact = availabilityImpact;
    }

    void setBaseScore(double baseScore) {
        this.baseScore = baseScore;
    }
}
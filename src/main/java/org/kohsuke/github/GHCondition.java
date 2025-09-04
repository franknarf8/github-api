package org.kohsuke.github;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.kohsuke.github.internal.EnumUtils;

import java.util.List;

@SuppressFBWarnings(value = { "UWF_UNWRITTEN_PUBLIC_OR_PROTECTED_FIELD", "UWF_UNWRITTEN_FIELD", "NP_UNWRITTEN_FIELD" },
        justification = "JSON API")
public class GHCondition {
    public static class GHConditionRefName {
        List<String> include;
        List<String> exclude;
    }

    public static class GHConditionRepoName {
        List<String> include;
        List<String> exclude;
        @JsonProperty("protected")
        Boolean isProtected;
    }

    public static class GHConditionRepoId {
        List<Integer> repositoryIds;
    }

    public enum GHConditionPropertySource {
        CUSTOM,
        SYSTEM,
        UNKNOWN
    }

    public static class GHConditionProperty {
        String name;
        List<String> propertyValues;
        String source;

        GHConditionPropertySource getSource() {
            return EnumUtils.getEnumOrDefault(GHConditionPropertySource.class, this.source, GHConditionPropertySource.UNKNOWN);
        }
    }

    public static class GHConditionPropertyMatcher {
        List<GHConditionPropertyMatcher> include;
        List<GHConditionPropertyMatcher> exclude;
    }

    private GHConditionRefName refName;
    private GHConditionRepoName repositoryName;
    private GHConditionRepoId repositoryId;
    private GHConditionProperty repositoryProperty;

    public GHConditionRefName getRefName() {
        return refName;
    }

    public GHConditionRepoName getRepositoryName() {
        return repositoryName;
    }

    public GHConditionRepoId getRepositoryId() {
        return repositoryId;
    }

    public GHConditionProperty getRepositoryProperty() {
        return repositoryProperty;
    }
}

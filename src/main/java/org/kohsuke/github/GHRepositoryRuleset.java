package org.kohsuke.github;

import com.infradna.tool.bridge_method_injector.WithBridgeMethods;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.kohsuke.github.internal.EnumUtils;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@SuppressFBWarnings(value = { "UWF_UNWRITTEN_PUBLIC_OR_PROTECTED_FIELD", "UWF_UNWRITTEN_FIELD", "NP_UNWRITTEN_FIELD" },
        justification = "JSON API")
public class GHRepositoryRuleset extends GitHubInteractiveObject {
    public enum RulesetSourceType {
        ORGANIZATION,
        REPOSITORY,
        UNKNOWN
    }

    public enum RulesetTarget {
        BRANCH,
        TAG,
        REPOSITORY,
        UNKNOWN
    }

    public enum RulesetEnforcement {
        DISABLED,
        ACTIVE,
        EVALUATE,
        UNKNOWN
    }

    public enum RulesetBypass {
        ALWAYS,
        PULL_REQUESTS_ONLY,
        NEVER,
        UNKNOWN
    }

    public enum GHActorType {
        INTEGRATION,
        ORGANIZATION_ADMIN,
        REPOSITORY_ROLE,
        TEAM,
        DEPLOY_KEY,
        UNKNOWN
    }

    public enum GHActorBypassMode {
        ALWAYS,
        PULL_REQUESTS,
        UNKNOWN
    }

    public static class GHActor {
        int actorId;
        GHActorType actorType;
        GHActorBypassMode bypassMode;

        private GHActor(int actorId, GHActorType actorType, GHActorBypassMode bypassMode) {
            this.actorId = actorId;
            this.actorType = actorType;
            this.bypassMode = bypassMode;
        }
    }

    private long id;
    private String name;
    private String source;
    private String sourceType;
    private String target;
    private String enforcement;
    private String currentUserCanBypass;
    private String nodeId;
    private List<GHActor> bypassActors;
    private GHCondition conditions;
    private List<GHRepositoryRule> rules;

    private String createdAt;
    private String updatedAt;

    protected GHObject owner;

    public long getId() {
        return this.id;
    }

    public List<GHRepositoryRule> getRules() {
        return this.rules;
    }

    public GHCondition getConditions() {
        return this.conditions;
    }

    public String getName() {
        return this.name;
    }

    public String getNodeId() {
        return this.nodeId;
    }

    @WithBridgeMethods(value = Date.class, adapterMethod = "instantToDate")
    public Instant getCreatedAt() {
        return GitHubClient.parseInstant(createdAt);
    }

    @WithBridgeMethods(value = Date.class, adapterMethod = "instantToDate")
    public Instant getUpdatedAt() {
        return GitHubClient.parseInstant(updatedAt);
    }

    public String getSource() {
        return this.source;
    }

    public RulesetSourceType getSourceType() {
        return EnumUtils.getEnumOrDefault(RulesetSourceType.class, this.sourceType, RulesetSourceType.UNKNOWN);
    }

    public RulesetTarget getTarget() {
        return EnumUtils.getEnumOrDefault(RulesetTarget.class, this.target, RulesetTarget.UNKNOWN);
    }

    public RulesetEnforcement getEnforcement() {
        return EnumUtils.getEnumOrDefault(RulesetEnforcement.class, this.enforcement, RulesetEnforcement.UNKNOWN);
    }

    public RulesetBypass getCurrentUserCanBypass() {
        return EnumUtils.getEnumOrDefault(RulesetBypass.class, this.currentUserCanBypass, RulesetBypass.UNKNOWN);
    }

    GHRepositoryRuleset lateBind(GHRepository repo) {
        this.owner = repo;
        return this;
    }
}

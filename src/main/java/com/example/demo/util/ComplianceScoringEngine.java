@Entity
@Table(name = "compliance_rules")
public class ComplianceRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String ruleName;

    private String ruleDescription;
    private String matchType;
    private Double threshold;

    // REQUIRED BY TEST
    private Double score;

    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        if (this.score == null) {
            this.score = 0.0;
        }
    }

    public Double getScore() {
        return score;
    }
}

import styles from './PasswordRules.module.css';

function PasswordRules({ rules }) {
  return (
    <div className={styles.passwordRules}>
      <p className={styles.title}>Parola trebuie să conțină:</p>
      <ul className={styles.rulesList}>
        {rules.map((rule) => (
          <li
            key={rule.id}
            className={`${styles.ruleItem} ${rule.met ? styles.met : styles.unmet}`}
          >
            <span className={styles.icon}>
              {rule.met ? (
                <svg
                  width="16"
                  height="16"
                  viewBox="0 0 24 24"
                  fill="none"
                  stroke="currentColor"
                  strokeWidth="2"
                  strokeLinecap="round"
                  strokeLinejoin="round"
                >
                  <polyline points="20 6 9 17 4 12" />
                </svg>
              ) : (
                <svg
                  width="16"
                  height="16"
                  viewBox="0 0 24 24"
                  fill="none"
                  stroke="currentColor"
                  strokeWidth="2"
                  strokeLinecap="round"
                  strokeLinejoin="round"
                >
                  <circle cx="12" cy="12" r="10" />
                </svg>
              )}
            </span>
            <span className={styles.ruleLabel}>{rule.label}</span>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default PasswordRules;

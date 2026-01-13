import styles from './FormField.module.css';

function FormField({ 
  label, 
  type = 'text', 
  name, 
  value, 
  onChange, 
  error,
  placeholder 
}) {
  return (
    <div className={styles.formField}>
      <label htmlFor={name} className={styles.label}>
        {label}
      </label>
      <input
        type={type}
        id={name}
        name={name}
        value={value}
        onChange={onChange}
        className={`${styles.input} ${error ? styles.inputError : ''}`}
        placeholder={placeholder}
      />
      {error && <span className={styles.error}>{error}</span>}
    </div>
  );
}

export default FormField;

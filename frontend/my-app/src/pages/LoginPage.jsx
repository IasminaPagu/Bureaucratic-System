import styles from './CommonPage.module.css';

function LoginPage() {
  return (
    <div className={styles.page}>
      <h1>Autentificare</h1>
      <p className={styles.description}>
        Pagina de autentificare va fi implementată în etapele următoare. 
        Aici utilizatorii vor putea să se autentifice în sistem folosind 
        credențialele lor sau să creeze un cont nou.
      </p>
      <div className={styles.placeholder}>
        <p>🔐 Formular de autentificare</p>
        <p>📝 Link către înregistrare</p>
        <p>🔑 Recuperare parolă</p>
      </div>
    </div>
  );
}

export default LoginPage;

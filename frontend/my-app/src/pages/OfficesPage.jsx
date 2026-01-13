import styles from './CommonPage.module.css';

function OfficesPage() {
  return (
    <div className={styles.page}>
      <h1>Birouri Publice</h1>
      <p className={styles.description}>
        Aici veți găsi lista tuturor birourilor publice disponibile în sistem, 
        împreună cu ghișeele lor și tipurile de documente pe care le emit. 
        Fiecare birou are caracteristici specifice și timpi de procesare diferiți.
      </p>
      <div className={styles.placeholder}>
        <p>🏢 Direcția de Evidență a Persoanelor</p>
        <p>💰 Administrația Financiară</p>
        <p>🏠 Oficiul de Cadastru și Publicitate Imobiliară</p>
        <p>🚗 Serviciul de Înmatriculări Auto</p>
        <p>📋 Registrul Comerțului</p>
      </div>
    </div>
  );
}

export default OfficesPage;

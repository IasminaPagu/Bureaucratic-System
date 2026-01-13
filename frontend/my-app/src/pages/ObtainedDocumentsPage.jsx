import styles from './CommonPage.module.css';

function ObtainedDocumentsPage() {
  return (
    <div className={styles.page}>
      <h1>Documente Obținute</h1>
      <p className={styles.description}>
        Vizualizați toate cererile dumneavoastră active și documentele obținute. 
        Monitorizați în timp real statusul fiecărei cereri, poziția în coadă, 
        și timpul estimat până la finalizare.
      </p>
      <div className={styles.placeholder}>
        <p>📋 Cereri active și statusul lor</p>
        <p>⏱️ Poziție în coadă</p>
        <p>✅ Documente finalizate</p>
        <p>🔔 Notificări și actualizări</p>
        <p>📥 Descărcare documente</p>
      </div>
    </div>
  );
}

export default ObtainedDocumentsPage;

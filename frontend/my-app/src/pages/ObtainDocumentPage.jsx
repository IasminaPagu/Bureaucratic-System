import styles from './CommonPage.module.css';

function ObtainDocumentPage() {
  return (
    <div className={styles.page}>
      <h1>Obținere Document</h1>
      <p className={styles.description}>
        Selectați documentul pe care doriți să-l obțineți. Sistemul va analiza 
        automat toate dependențele și va genera un plan de acțiune cu pașii 
        necesari și documentele intermediare care trebuie obținute mai întâi.
      </p>
      <div className={styles.placeholder}>
        <p>📋 Selector de tip document</p>
        <p>🔍 Vizualizare dependențe</p>
        <p>📊 Graf de documente necesare</p>
        <p>▶️ Inițiere proces de obținere</p>
      </div>
    </div>
  );
}

export default ObtainDocumentPage;

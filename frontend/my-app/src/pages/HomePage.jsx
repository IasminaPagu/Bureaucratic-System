import styles from './HomePage.module.css';

function HomePage() {
  return (
    <div className={styles.homePage}>
      <div className={styles.hero}>
        <h1>Bine ați venit la Biroul.ro</h1>
        <p>
          Platforma de simulare a sistemului birocratic românesc. Experimentați procesul 
          complex de obținere a documentelor într-un mediu digital interactiv.
        </p>
      </div>

      <div className={styles.section}>
        <h2>Ce este Biroul.ro?</h2>
        <p>
          Biroul.ro este o aplicație web care simulează ecosistemul birocratic complex 
          specific instituțiilor publice. Aici veți experimenta procesul real de obținere 
          a documentelor, cu toate provocările sale: dependențe între documente, cozi la 
          ghișee, și chiar pauzele de cafea ale funcționarilor!
        </p>
        <p>
          Inspirată de platforme precum Ghișeul.ro, aplicația noastră vă oferă o experiență 
          educativă și interactivă în navigarea birocrației administrative.
        </p>
      </div>

      <div className={styles.section}>
        <h2>Cum funcționează sistemul?</h2>
        <div className={styles.conceptList}>
          <div className={styles.conceptItem}>
            <h3>📄 Dependențe între documente</h3>
            <p>
              Pentru a obține documentul final dorit, veți avea nevoie de mai multe documente 
              intermediare. De exemplu, pentru un certificat de atestare fiscală, poate fi 
              necesar mai întâi un certificat de atestare fiscală locală, un act de identitate 
              actualizat, și o dovadă de domiciliu.
            </p>
          </div>

          <div className={styles.conceptItem}>
            <h3>🏢 Birouri și ghișee multiple</h3>
            <p>
              Sistemul include mai multe birouri publice (Evidența Populației, Finanțe, 
              Cadastru, etc.), fiecare cu propriile ghișee. Fiecare ghișeu procesează cereri 
              specifice și are propria coadă de așteptare.
            </p>
          </div>

          <div className={styles.conceptItem}>
            <h3>⏰ Cozi și timpi de procesare</h3>
            <p>
              Ca în realitate, veți aștepta la rând! Fiecare cerere este adăugată într-o coadă 
              și procesată în ordine. Timpul de procesare variază în funcție de tipul documentului 
              și complexitatea sa.
            </p>
          </div>

          <div className={styles.conceptItem}>
            <h3>☕ Pauze de cafea</h3>
            <p>
              Funcționarii pot lua pauze de cafea aleatorii! În timpul acestor pauze, procesarea 
              cererilor este temporar suspendată, simulând întârzierile neprevăzute din viața 
              reală.
            </p>
          </div>
        </div>
      </div>

      <div className={styles.section}>
        <h2>Urmărirea progresului</h2>
        <p>
          În secțiunea <strong>Documente obținute</strong>, veți putea vedea în timp real:
        </p>
        <ul style={{ marginLeft: '2rem', marginTop: '1rem' }}>
          <li style={{ marginBottom: '0.5rem' }}>✓ Cererile dumneavoastră active și statusul lor</li>
          <li style={{ marginBottom: '0.5rem' }}>✓ Poziția în coadă pentru fiecare cerere</li>
          <li style={{ marginBottom: '0.5rem' }}>✓ Documentele obținute cu succes</li>
          <li style={{ marginBottom: '0.5rem' }}>✓ Timpul estimat până la finalizare</li>
        </ul>
        
        <div className={styles.highlight}>
          <p>
            <strong>Notă:</strong> Sistemul funcționează în timp real. Veți primi notificări 
            când documentele sunt gata sau când apar întârzieri neprevăzute.
          </p>
        </div>
      </div>

      <div className={styles.section}>
        <h2>Cum începi?</h2>
        <div className={styles.stepsGrid}>
          <div className={styles.stepCard}>
            <div className={styles.stepNumber}>1</div>
            <h3>Autentifică-te</h3>
            <p>
              Creează un cont sau autentifică-te folosind pagina de Login pentru a accesa 
              sistemul.
            </p>
          </div>

          <div className={styles.stepCard}>
            <h3 className={styles.stepNumber}>2</h3>
            <h3>Explorează birourile</h3>
            <p>
              Vizitează secțiunea Birouri pentru a vedea toate instituțiile disponibile și 
              documentele pe care le emit.
            </p>
          </div>

          <div className={styles.stepCard}>
            <div className={styles.stepNumber}>3</div>
            <h3>Selectează documentul</h3>
            <p>
              În secțiunea Obținere document, alege documentul final pe care dorești să-l 
              obții.
            </p>
          </div>

          <div className={styles.stepCard}>
            <div className={styles.stepNumber}>4</div>
            <h3>Verifică dependențele</h3>
            <p>
              Sistemul îți va arăta toate documentele intermediare necesare și ordinea în 
              care trebuie obținute.
            </p>
          </div>

          <div className={styles.stepCard}>
            <div className={styles.stepNumber}>5</div>
            <h3>Urmărește progresul</h3>
            <p>
              Monitorizează statusul cererilor tale în timp real în secțiunea Documente 
              obținute.
            </p>
          </div>
        </div>
      </div>
    </div>
  );
}

export default HomePage;

import { useState } from 'react';
import Tabs from '../components/Tabs';
import FormField from '../components/FormField';
import {
  validateEmail,
  validatePassword,
  validateRequired,
  validatePasswordMatch,
} from '../utils/validation';
import styles from './LoginPage.module.css';

function LoginPage() {
  const [activeTab, setActiveTab] = useState('login');
  const [successMessage, setSuccessMessage] = useState('');

  // State pentru formularul de conectare
  const [loginForm, setLoginForm] = useState({
    email: '',
    password: '',
  });

  const [loginErrors, setLoginErrors] = useState({});

  // State pentru formularul de creare cont
  const [registerForm, setRegisterForm] = useState({
    email: '',
    nume: '',
    prenume: '',
    password: '',
    confirmPassword: '',
  });

  const [registerErrors, setRegisterErrors] = useState({});

  const tabs = [
    { id: 'login', label: 'Conectare' },
    { id: 'register', label: 'Creare cont' },
  ];

  const handleTabChange = (tabId) => {
    setActiveTab(tabId);
    setSuccessMessage('');
    setLoginErrors({});
    setRegisterErrors({});
  };

  const handleLoginChange = (e) => {
    const { name, value } = e.target;
    setLoginForm((prev) => ({ ...prev, [name]: value }));
    // Șterge eroarea pentru câmpul modificat
    if (loginErrors[name]) {
      setLoginErrors((prev) => ({ ...prev, [name]: null }));
    }
  };

  const handleRegisterChange = (e) => {
    const { name, value } = e.target;
    setRegisterForm((prev) => ({ ...prev, [name]: value }));
    // Șterge eroarea pentru câmpul modificat
    if (registerErrors[name]) {
      setRegisterErrors((prev) => ({ ...prev, [name]: null }));
    }
  };

  const validateLoginForm = () => {
    const errors = {};

    const emailError = validateEmail(loginForm.email);
    if (emailError) errors.email = emailError;

    const passwordError = validatePassword(loginForm.password);
    if (passwordError) errors.password = passwordError;

    return errors;
  };

  const validateRegisterForm = () => {
    const errors = {};

    const emailError = validateEmail(registerForm.email);
    if (emailError) errors.email = emailError;

    const numeError = validateRequired(registerForm.nume, 'Numele');
    if (numeError) errors.nume = numeError;

    const prenumeError = validateRequired(registerForm.prenume, 'Prenumele');
    if (prenumeError) errors.prenume = prenumeError;

    const passwordError = validatePassword(registerForm.password);
    if (passwordError) errors.password = passwordError;

    const confirmPasswordError = validatePassword(registerForm.confirmPassword);
    if (confirmPasswordError) {
      errors.confirmPassword = confirmPasswordError;
    } else {
      const matchError = validatePasswordMatch(
        registerForm.password,
        registerForm.confirmPassword
      );
      if (matchError) errors.confirmPassword = matchError;
    }

    return errors;
  };

  const handleLoginSubmit = (e) => {
    e.preventDefault();
    setSuccessMessage('');

    const errors = validateLoginForm();

    if (Object.keys(errors).length > 0) {
      setLoginErrors(errors);
      return;
    }

    // Simulare autentificare reușită
    console.log('Autentificare cu:', loginForm);
    setSuccessMessage('Autentificare reușită! Bine ai revenit.');
    setLoginForm({ email: '', password: '' });
    setLoginErrors({});
  };

  const handleRegisterSubmit = (e) => {
    e.preventDefault();
    setSuccessMessage('');

    const errors = validateRegisterForm();

    if (Object.keys(errors).length > 0) {
      setRegisterErrors(errors);
      return;
    }

    // Simulare creare cont reușită
    console.log('Creare cont cu:', registerForm);
    setSuccessMessage('Contul a fost creat cu succes! Bine ai venit.');
    setRegisterForm({
      email: '',
      nume: '',
      prenume: '',
      password: '',
      confirmPassword: '',
    });
    setRegisterErrors({});
  };

  return (
    <div className={styles.loginPage}>
      <div className={styles.container}>
        <h1 className={styles.title}>Autentificare</h1>

        <div className={styles.card}>
          <Tabs tabs={tabs} activeTab={activeTab} onTabChange={handleTabChange} />

          {successMessage && (
            <div className={styles.successMessage}>{successMessage}</div>
          )}

          {activeTab === 'login' && (
            <form onSubmit={handleLoginSubmit} className={styles.form}>
              <FormField
                label="Adresă de e-mail"
                type="email"
                name="email"
                value={loginForm.email}
                onChange={handleLoginChange}
                error={loginErrors.email}
                placeholder="exemplu@email.com"
              />

              <FormField
                label="Parolă"
                type="password"
                name="password"
                value={loginForm.password}
                onChange={handleLoginChange}
                error={loginErrors.password}
                placeholder="Introduceți parola"
              />

              <button type="submit" className={styles.submitButton}>
                Conectare
              </button>
            </form>
          )}

          {activeTab === 'register' && (
            <form onSubmit={handleRegisterSubmit} className={styles.form}>
              <FormField
                label="Adresă de e-mail"
                type="email"
                name="email"
                value={registerForm.email}
                onChange={handleRegisterChange}
                error={registerErrors.email}
                placeholder="exemplu@email.com"
              />

              <FormField
                label="Nume"
                type="text"
                name="nume"
                value={registerForm.nume}
                onChange={handleRegisterChange}
                error={registerErrors.nume}
                placeholder="Introduceți numele"
              />

              <FormField
                label="Prenume"
                type="text"
                name="prenume"
                value={registerForm.prenume}
                onChange={handleRegisterChange}
                error={registerErrors.prenume}
                placeholder="Introduceți prenumele"
              />

              <FormField
                label="Parolă"
                type="password"
                name="password"
                value={registerForm.password}
                onChange={handleRegisterChange}
                error={registerErrors.password}
                placeholder="Minim 6 caractere"
              />

              <FormField
                label="Confirmare parolă"
                type="password"
                name="confirmPassword"
                value={registerForm.confirmPassword}
                onChange={handleRegisterChange}
                error={registerErrors.confirmPassword}
                placeholder="Reintroduceți parola"
              />

              <button type="submit" className={styles.submitButton}>
                Creare cont
              </button>
            </form>
          )}
        </div>
      </div>
    </div>
  );
}

export default LoginPage;

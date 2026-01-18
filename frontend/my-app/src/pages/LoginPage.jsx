import { useState } from 'react';
import Tabs from '../components/Tabs';
import FormField from '../components/FormField';
import PasswordField from '../components/forms/PasswordField';
import PasswordRules from '../components/forms/PasswordRules';
import { useNavigate } from "react-router-dom";
import { login as loginApi, register as registerApi } from "../services/authService";

import {
  validateEmail,
  validatePassword,
  validateRequired,
  validatePasswordMatch,
} from '../utils/validation';

import { validateStrongPassword, checkPasswordRules } from '../utils/passwordRules';
import styles from './LoginPage.module.css';

function LoginPage() {
  const navigate = useNavigate();

  const [activeTab, setActiveTab] = useState('login');

  // mesaje UI
  const [successMessage, setSuccessMessage] = useState('');
  const [serverError, setServerError] = useState('');
  const [loading, setLoading] = useState(false);

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

  const resetMessages = () => {
    setSuccessMessage('');
    setServerError('');
  };

  const handleTabChange = (tabId) => {
    setActiveTab(tabId);
    resetMessages();
    setLoginErrors({});
    setRegisterErrors({});
  };

  const handleLoginChange = (e) => {
    const { name, value } = e.target;
    setLoginForm((prev) => ({ ...prev, [name]: value }));

    if (loginErrors[name]) {
      setLoginErrors((prev) => ({ ...prev, [name]: null }));
    }
  };

  const handleRegisterChange = (e) => {
    const { name, value } = e.target;
    setRegisterForm((prev) => ({ ...prev, [name]: value }));

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

    // Validare parolă puternică pentru register
    const passwordError = validateStrongPassword(registerForm.password);
    if (passwordError) errors.password = passwordError;

    // Confirmare parolă obligatorie + match
    if (!registerForm.confirmPassword || registerForm.confirmPassword.trim() === '') {
      errors.confirmPassword = 'Confirmarea parolei este obligatorie';
    } else {
      const matchError = validatePasswordMatch(
        registerForm.password,
        registerForm.confirmPassword
      );
      if (matchError) errors.confirmPassword = matchError;
    }

    return errors;
  };

  const handleLoginSubmit = async (e) => {
    e.preventDefault();
    resetMessages();

    const errors = validateLoginForm();
    if (Object.keys(errors).length > 0) {
      setLoginErrors(errors);
      return;
    }

    try {
      setLoading(true);

      await loginApi(loginForm.email, loginForm.password);

      setSuccessMessage('Autentificare reușită! Bine ai revenit.');
      setLoginErrors({});
      setLoginForm({ email: '', password: '' });

      // redirect după login
      navigate('/', { replace: true });
    } catch (err) {
      setServerError(err?.message || 'Autentificare eșuată.');
    } finally {
      setLoading(false);
    }
  };

  const handleRegisterSubmit = async (e) => {
    e.preventDefault();
    resetMessages();

    const errors = validateRegisterForm();
    if (Object.keys(errors).length > 0) {
      setRegisterErrors(errors);
      return;
    }

    try {
      setLoading(true);

      // Trimitem confirmPassword, iar authService îl mapează în passwordConfirm pentru backend
      await registerApi({
        email: registerForm.email,
        password: registerForm.password,
        confirmPassword: registerForm.confirmPassword,
        nume: registerForm.nume,
        prenume: registerForm.prenume,
      });

      setSuccessMessage('Contul a fost creat cu succes! Te poți autentifica acum.');
      setRegisterErrors({});

      setRegisterForm({
        email: '',
        nume: '',
        prenume: '',
        password: '',
        confirmPassword: '',
      });

      setActiveTab('login');
    } catch (err) {
      setServerError(err?.message || 'Crearea contului a eșuat.');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className={styles.loginPage}>
      <div className={styles.container}>
        <h1 className={styles.title}>Autentificare</h1>

        <div className={styles.card}>
          <Tabs tabs={tabs} activeTab={activeTab} onTabChange={handleTabChange} />

          {serverError && (
            <div className={styles.errorMessage}>
              {serverError}
            </div>
          )}

          {successMessage && (
            <div className={styles.successMessage}>
              {successMessage}
            </div>
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

              <button
                type="submit"
                className={styles.submitButton}
                disabled={loading}
              >
                {loading ? 'Se conectează...' : 'Conectare'}
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

              <PasswordField
                label="Parolă"
                name="password"
                value={registerForm.password}
                onChange={handleRegisterChange}
                error={registerErrors.password}
                placeholder="Creează o parolă puternică"
              />

              {registerForm.password && (
                <PasswordRules rules={checkPasswordRules(registerForm.password)} />
              )}

              <PasswordField
                label="Confirmare parolă"
                name="confirmPassword"
                value={registerForm.confirmPassword}
                onChange={handleRegisterChange}
                error={registerErrors.confirmPassword}
                placeholder="Reintroduceți parola"
              />

              <button
                type="submit"
                className={styles.submitButton}
                disabled={loading}
              >
                {loading ? 'Se creează contul...' : 'Creare cont'}
              </button>
            </form>
          )}
        </div>
      </div>
    </div>
  );
}

export default LoginPage;

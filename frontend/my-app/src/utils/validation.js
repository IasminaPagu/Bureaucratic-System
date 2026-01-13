/**
 * Validare pentru adresa de email
 */
export const validateEmail = (email) => {
  if (!email || email.trim() === '') {
    return 'Adresa de e-mail este obligatorie';
  }
  
  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  if (!emailRegex.test(email)) {
    return 'Adresa de e-mail nu este validă';
  }
  
  return null;
};

/**
 * Validare pentru parolă
 */
export const validatePassword = (password) => {
  if (!password || password.trim() === '') {
    return 'Parola este obligatorie';
  }
  
  if (password.length < 6) {
    return 'Parola trebuie să conțină cel puțin 6 caractere';
  }
  
  return null;
};

/**
 * Validare pentru câmp text obligatoriu
 */
export const validateRequired = (value, fieldName) => {
  if (!value || value.trim() === '') {
    return `${fieldName} este obligatoriu`;
  }
  
  return null;
};

/**
 * Validare pentru confirmare parolă
 */
export const validatePasswordMatch = (password, confirmPassword) => {
  if (password !== confirmPassword) {
    return 'Parolele nu coincid';
  }
  
  return null;
};

/**
 * Regulile pentru parola puternică
 */
export const passwordRules = [
  {
    id: 'minLength',
    label: 'Minim 8 caractere',
    test: (password) => password.length >= 8,
  },
  {
    id: 'lowercase',
    label: 'Cel puțin o literă mică',
    test: (password) => /[a-z]/.test(password),
  },
  {
    id: 'uppercase',
    label: 'Cel puțin o literă mare',
    test: (password) => /[A-Z]/.test(password),
  },
  {
    id: 'digit',
    label: 'Cel puțin o cifră',
    test: (password) => /[0-9]/.test(password),
  },
  {
    id: 'special',
    label: 'Cel puțin un caracter special (!@#$%^&*)',
    test: (password) => /[!@#$%^&*(),.?":{}|<>]/.test(password),
  },
];

/**
 * Verifică dacă parola respectă toate regulile
 */
export const validateStrongPassword = (password) => {
  if (!password || password.trim() === '') {
    return 'Parola este obligatorie';
  }

  const failedRules = passwordRules.filter((rule) => !rule.test(password));

  if (failedRules.length > 0) {
    return 'Parola nu îndeplinește toate criteriile necesare';
  }

  return null;
};

/**
 * Verifică starea fiecărei reguli pentru o parolă dată
 */
export const checkPasswordRules = (password) => {
  return passwordRules.map((rule) => ({
    ...rule,
    met: rule.test(password),
  }));
};

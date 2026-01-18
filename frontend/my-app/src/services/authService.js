import { apiFetch } from "./apiClient";

const TOKEN_KEY = "auth_token";
const TOKEN_TYPE_KEY = "auth_tokenType";
const EMAIL_KEY = "auth_email";

export async function login(email, password) {
  const data = await apiFetch("/api/auth/login", {
    method: "POST",
    auth: false,
    body: { email, password },
  });

  const token = data?.token;
  if (!token) throw new Error("Răspuns invalid de la server: lipsește token-ul.");

  localStorage.setItem(TOKEN_KEY, token);
  localStorage.setItem(TOKEN_TYPE_KEY, data?.tokenType || "Bearer");
  localStorage.setItem(EMAIL_KEY, email);

  return data;
}

export async function register(payload) {
  // Standardizează payload-ul către backend (contract API)
  const body = {
    email: payload.email,
    password: payload.password,

    // backend cere "passwordConfirm"
    passwordConfirm: payload.passwordConfirm ?? payload.confirmPassword,

    // backend folosește firstName/lastName
    firstName: payload.firstName ?? payload.nume,
    lastName: payload.lastName ?? payload.prenume,
  };

  return apiFetch("/api/auth/register", {
    method: "POST",
    auth: false,
    body,
  });
}

export function logout() {
  localStorage.removeItem(TOKEN_KEY);
  localStorage.removeItem(TOKEN_TYPE_KEY);
  localStorage.removeItem(EMAIL_KEY);
}

export function isAuthenticated() {
  return !!localStorage.getItem(TOKEN_KEY);
}

export function getToken() {
  return localStorage.getItem(TOKEN_KEY);
}

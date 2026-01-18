const BASE_URL = import.meta.env.VITE_API_BASE_URL;

function getToken() {
  return localStorage.getItem("auth_token");
}

export async function apiFetch(path, { method = "GET", body, auth = true, headers = {} } = {}) {
  const finalHeaders = { ...headers };

  // JSON implicit (dacă nu e FormData)
  if (body && !(body instanceof FormData)) {
    finalHeaders["Content-Type"] = finalHeaders["Content-Type"] || "application/json";
  }

  if (auth) {
    const token = getToken();
    if (token) finalHeaders["Authorization"] = `Bearer ${token}`;
  }

  const res = await fetch(`${BASE_URL}${path}`, {
    method,
    headers: finalHeaders,
    body: body ? (body instanceof FormData ? body : JSON.stringify(body)) : undefined,
  });

  // încearcă să parsezi răspunsul (JSON sau text)
  const text = await res.text();
  let data = null;
  try {
    data = text ? JSON.parse(text) : null;
  } catch {
    data = text || null;
  }

  if (!res.ok) {
    // încearcă să scoți un mesaj util din backend
    const msg =
      (data && typeof data === "object" && data.message) ? data.message :
      (typeof data === "string" && data) ? data :
      `Eroare la server (${res.status})`;

    const err = new Error(msg);
    err.status = res.status;
    err.data = data;
    throw err;
  }

  return data;
}

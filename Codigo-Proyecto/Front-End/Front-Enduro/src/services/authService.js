import axios from "axios";

const API_URL = "http://localhost:8080/api/usuarios/Login";

export const login = async (username, password) => {
  try {
    const params = new URLSearchParams();
    params.append("Nickname", username);
    params.append("contrasena", password);

    const response = await axios.post(API_URL, params, {
      headers: { "Content-Type": "application/x-www-form-urlencoded" },
      // Quita responseType: "text"
    });

    // Soporta booleano o string
    return response.data === true || response.data === "true";
  } catch (error) {
    throw error.response?.data || "Error al iniciar sesión";
  }
};

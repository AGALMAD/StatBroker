import axios from "axios";
import { useAuthStore } from "stores/useAuthStore";

const api = axios.create({
  baseURL: `${import.meta.env.VITE_SERVER_URL}/api`,
});

api.interceptors.request.use((config) => {
  const { authTokens } = useAuthStore.getState();

  if (authTokens?.accessToken) {
    config.headers.Authorization = `Bearer ${authTokens.accessToken}`;
  }
  return config;
});

export default api;

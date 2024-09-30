import { useState, useEffect } from "react";
import { useRouter } from "next/router";
import { getToken, logout } from "../services/authService";

export function useAuth() {
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const router = useRouter();

  useEffect(() => {
    const token = getToken();
    if (token) {
      setIsAuthenticated(true);
    } else {
      setIsAuthenticated(false);
      router.push("/login");
    }
  }, [router]);

  const handleLogout = () => {
    logout();
    setIsAuthenticated(false);
    router.push("/login");
  };

  return { isAuthenticated, handleLogout };
}

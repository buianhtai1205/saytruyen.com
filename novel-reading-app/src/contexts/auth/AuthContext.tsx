import { createContext } from 'react';

interface AuthContextType {
    isLoggedIn: boolean;
    setIsLoggedIn: (value: boolean) => void;
}

export const AuthContext = createContext<AuthContextType | undefined>(
    undefined
);

import React, { useState, useEffect } from 'react';
import { AuthContext } from './AuthContext';
import { userInfo, refreshToken } from '@api/services/user-service/authService';
import { MESSAGE_ID } from '@api/common/messageIdConstant';

export const AuthProvider = ({ children }: { children: React.ReactNode }) => {
    const [isLoggedIn, setIsLoggedIn] = useState(false);

    const refreshTokenRequest = async (token: string) => {
        const response = await refreshToken(token);
        if (response.code === 200) {
            localStorage.setItem('token', response.data.token);
            checkLogin();
        }
    };

    const checkLogin = async () => {
        const token = localStorage.getItem('token');
        if (token) {
            try {
                const response = await userInfo();
                if (response.code === 200) {
                    setIsLoggedIn(true);
                }
            } catch (error: any) {
                switch (error?.messageId) {
                    case MESSAGE_ID.CM_E000006.code:
                        console.log('call refresh token');
                        let refreshToken = localStorage.getItem('refreshToken');
                        if (refreshToken) {
                            refreshTokenRequest(refreshToken.toString());
                        } else {
                            setIsLoggedIn(false);
                        }
                        break;
                    default:
                        setIsLoggedIn(false);
                }
            }
        }
    };

    useEffect(() => {
        checkLogin();
    }, []);

    return (
        <AuthContext.Provider value={{ isLoggedIn, setIsLoggedIn }}>
            {children}
        </AuthContext.Provider>
    );
};

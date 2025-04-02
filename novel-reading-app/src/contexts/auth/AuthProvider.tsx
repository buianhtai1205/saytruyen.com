import React, { useState, useEffect } from 'react';
import { AuthContext } from './AuthContext';
import { userInfo, refreshToken } from '@api/services/user-service/authService';
import { MESSAGE_ID } from '@api/common/messageIdConstant';
import { toast } from 'react-toastify';
import { Bounce } from 'react-toastify';

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
        toast('🦄 Wow so easy!', {
            position: 'top-right',
            autoClose: 5000,
            hideProgressBar: false,
            closeOnClick: false,
            pauseOnHover: true,
            draggable: true,
            progress: undefined,
            theme: 'light',
            transition: Bounce,
        });
        checkLogin();
    }, []);

    return (
        <AuthContext.Provider value={{ isLoggedIn, setIsLoggedIn }}>
            {children}
        </AuthContext.Provider>
    );
};

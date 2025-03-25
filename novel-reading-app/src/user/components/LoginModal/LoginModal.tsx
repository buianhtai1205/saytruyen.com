import React, { useState } from 'react';
import clsx from 'clsx';
import styles from './LoginModal.module.scss';
import logo from '../../assets/images/logo150.png';
import { DEFAULT } from '@api/common/defaultConstants';
import { login, signUp } from '@api/services/user-service/authService';

interface LoginModalProps {
    isOpen: boolean;
    type: string;
    onClose: () => void;
}

const LoginModal = ({ isOpen, type, onClose }: LoginModalProps) => {
    const [currentType, setCurrentType] = useState(type);
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState('');

    const handleTypeChange = () => {
        if (currentType === DEFAULT.LOGIN_TYPE.LOGIN) {
            setCurrentType(DEFAULT.LOGIN_TYPE.REGISTER);
        } else {
            setCurrentType(DEFAULT.LOGIN_TYPE.LOGIN);
        }
    };

    const handleSubmit = async () => {
        try {
            setLoading(true);
            setError('');

            if (currentType === DEFAULT.LOGIN_TYPE.REGISTER) {
                if (password !== confirmPassword) {
                    setError('Mật khẩu không khớp');
                    return;
                }
                const response = await signUp({
                    email,
                    password,
                    username: email, // Using email as username
                    fullName: email.split('@')[0], // These fields can be updated later
                    gender: 0,
                    address: '',
                    imageUrl: '',
                    backgroundUrl: '',
                });
                if (response.code === 200) {
                    setCurrentType(DEFAULT.LOGIN_TYPE.LOGIN);
                } else {
                    setError(response.message || 'Đăng ký thất bại');
                }
            } else {
                const response = await login({
                    username: email,
                    password,
                });
                if (response.code === 200) {
                    // Store tokens and close modal
                    localStorage.setItem('token', response.data.token);
                    localStorage.setItem(
                        'refreshToken',
                        response.data.refreshToken
                    );
                    onClose();
                } else {
                    setError(response.message || 'Đăng nhập thất bại');
                }
            }
        } catch (err) {
            setError('Đã có lỗi xảy ra');
        } finally {
            setLoading(false);
        }
    };

    if (!isOpen) return null;

    return (
        <div className={clsx(styles.overlay)}>
            <div className={clsx(styles.modal)}>
                <div className={clsx(styles.modalHeader)}>
                    <img src={logo} alt="logo" className={clsx(styles.logo)} />
                    {currentType === DEFAULT.LOGIN_TYPE.LOGIN ? (
                        <h2>Đăng nhập</h2>
                    ) : (
                        <h2>Đăng ký</h2>
                    )}
                    <button
                        className={clsx(styles.closeButton)}
                        onClick={onClose}
                    >
                        ×
                    </button>
                </div>
                <div className={clsx(styles.modalBody)}>
                    {error && <div className={clsx(styles.error)}>{error}</div>}
                    <div className={clsx(styles.inputGroup)}>
                        <label>Email</label>
                        <input
                            type="email"
                            placeholder="Email"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                        />
                    </div>
                    <div className={clsx(styles.inputGroup)}>
                        <div className={clsx(styles.passwordHeader)}>
                            <label>Mật khẩu</label>
                            {currentType === DEFAULT.LOGIN_TYPE.LOGIN && (
                                <a
                                    href="#"
                                    className={clsx(styles.forgotPassword)}
                                >
                                    Quên mật khẩu
                                </a>
                            )}
                        </div>
                        <input
                            type="password"
                            placeholder="••••••••"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                        />
                    </div>
                    {currentType === DEFAULT.LOGIN_TYPE.REGISTER && (
                        <div className={clsx(styles.inputGroup)}>
                            <label>Nhập lại mật khẩu</label>
                            <input
                                type="password"
                                placeholder="••••••••"
                                value={confirmPassword}
                                onChange={(e) =>
                                    setConfirmPassword(e.target.value)
                                }
                            />
                        </div>
                    )}
                    <button
                        className={clsx(styles.loginButton)}
                        onClick={handleSubmit}
                        disabled={loading}
                    >
                        {loading
                            ? 'Đang xử lý...'
                            : currentType === DEFAULT.LOGIN_TYPE.LOGIN
                              ? 'Đăng nhập'
                              : 'Đăng ký'}
                    </button>
                    <div className={clsx(styles.registerPrompt)}>
                        {currentType === DEFAULT.LOGIN_TYPE.LOGIN
                            ? 'Chưa có tài khoản? '
                            : 'Đã có tài khoản? '}
                        <a href="#" onClick={handleTypeChange}>
                            {currentType === DEFAULT.LOGIN_TYPE.LOGIN
                                ? 'Đăng ký ngay'
                                : 'Đăng nhập ngay'}
                        </a>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default LoginModal;

import React, { useState } from 'react';
import clsx from 'clsx';
import styles from './LoginModal.module.scss';
import logo from '../../assets/images/logo150.png';
import { DEFAULT } from '@api/common/defaultConstants';

interface LoginModalProps {
    isOpen: boolean;
    type: string;
    onClose: () => void;
}

const LoginModal = ({ isOpen, type, onClose }: LoginModalProps) => {
    const [currentType, setCurrentType] = useState(type);

    const handleTypeChange = () => {
        if (currentType === DEFAULT.LOGIN_TYPE.LOGIN) {
            setCurrentType(DEFAULT.LOGIN_TYPE.REGISTER);
        } else {
            setCurrentType(DEFAULT.LOGIN_TYPE.LOGIN);
        }
    }

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
                    <button className={clsx(styles.closeButton)} onClick={onClose}>×</button>
                </div>
                <div className={clsx(styles.modalBody)}>
                    <div className={clsx(styles.inputGroup)}>
                        <label>Email</label>
                        <input type="email" placeholder="Email" />
                    </div>
                    <div className={clsx(styles.inputGroup)}>
                        <div className={clsx(styles.passwordHeader)}>
                            <label>Mật khẩu</label>
                            {currentType === DEFAULT.LOGIN_TYPE.LOGIN && <a href="#" className={clsx(styles.forgotPassword)}>Quên mật khẩu</a>}
                        </div>
                        <input type="password" placeholder="••••••••" />
                    </div>
                    {currentType === DEFAULT.LOGIN_TYPE.REGISTER && (
                        <div className={clsx(styles.inputGroup)}>
                            <label>Nhập lại mật khẩu</label>
                            <input type="password" placeholder="••••••••" />
                        </div>
                    )}
                    <button className={clsx(styles.loginButton)}>
                        {currentType === DEFAULT.LOGIN_TYPE.LOGIN ? 'Đăng nhập' : 'Đăng ký'}
                    </button>
                    <div className={clsx(styles.registerPrompt)}>
                        {currentType === DEFAULT.LOGIN_TYPE.LOGIN ? 'Chưa có tài khoản? ' : 'Đã có tài khoản? '}
                        <a href="#" onClick={handleTypeChange}>{currentType === DEFAULT.LOGIN_TYPE.LOGIN ? 'Đăng ký ngay' : 'Đăng nhập ngay'}</a>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default LoginModal;

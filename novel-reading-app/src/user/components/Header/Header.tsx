import React, { useState } from 'react';
import clsx from 'clsx';
import styles from './Header.module.scss';
import logo from '../../assets/images/logo150.png';
import { images } from '../../assets/svg';
import LoginModal from '../LoginModal/LoginModal';
import { DEFAULT } from '@api/common/defaultConstants';

const Header = () => {
    const [isSearchVisible, setIsSearchVisible] = useState(false);
    const [isMenuVisible, setIsMenuVisible] = useState(false);
    const [isThemeLight, setIsThemeLight] = useState(false);
    const [isLoginModalOpen, setIsLoginModalOpen] = useState(false);
    const [isRegisterModalOpen, setIsRegisterModalOpen] = useState(false);

    const toggleSearch = () => {
        setIsSearchVisible(!isSearchVisible);
    };

    const toggleMenu = () => {
        setIsMenuVisible(!isMenuVisible);
    };

    const toggleTheme = () => {
        setIsThemeLight(!isThemeLight);
    };

    const openLoginModal = () => {
        setIsLoginModalOpen(true);
        setIsMenuVisible(false);
    };

    const openRegisterModal = () => {
        setIsRegisterModalOpen(true);
        setIsMenuVisible(false);
    };

    return (
        <>
            <header className={clsx(styles.header)}>
                <div className={styles.topBar}>
                    <div onClick={toggleSearch}>
                        <img
                            src={images.searchIcon}
                            alt="search"
                            className={styles.searchIcon}
                        />
                    </div>
                    <img src={logo} alt="logo" className={styles.logo} />
                    <div onClick={toggleMenu}>
                        <img
                            src={images.menuIcon}
                            alt="menu"
                            className={clsx(styles.menuIcon)}
                        />
                    </div>
                </div>
                {isSearchVisible && (
                    <div className={clsx(styles.searchBar)}>
                        <input
                            type="text"
                            placeholder="Tìm truyện, tác giả..."
                            className={clsx(styles.searchInput)}
                        />
                        <button className={clsx(styles.searchButton)}>
                            <img
                                src={images.searchIcon}
                                alt="search"
                                className={clsx(styles.searchIconSub)}
                            />
                        </button>
                    </div>
                )}
            </header>
            {isMenuVisible && (
                <div className={clsx(styles.menuOverlay)}>
                    <div className={clsx(styles.menuBar)}>
                        <div className={clsx(styles.menuHeader)}>
                            <div
                                className={clsx(styles.toggleButton)}
                                onClick={toggleTheme}
                            >
                                {isThemeLight
                                    ? <span>🔆</span>
                                    : <span>🌙</span>}

                            </div>
                            <div
                                className={clsx(styles.closeButton)}
                                onClick={toggleMenu}
                            >
                                <span>✕</span>
                            </div>
                        </div>
                        <ul>
                            <li className={clsx(styles.menuItem)} onClick={openLoginModal}>
                                <span className={clsx(styles.icon)}>🗝️</span> Đăng nhập
                            </li>
                            <li className={clsx(styles.menuItem)} onClick={openRegisterModal}>
                                <span className={clsx(styles.icon)}>📝</span> Đăng ký
                                tài khoản
                            </li>
                            <li className={clsx(styles.menuItem)}>
                                <span className={clsx(styles.icon)}>📚</span> Đăng
                                truyện
                            </li>
                            <li className={clsx(styles.menuItem)}>
                                <span className={clsx(styles.icon)}>📖</span> Kho
                                truyện
                                <ul>
                                    <li>Truyện mới</li>
                                    <li>Truyện full</li>
                                </ul>
                            </li>
                            <li className={clsx(styles.menuItem)}>
                                <span className={clsx(styles.icon)}>🏆</span> Xếp hạng
                                <ul>
                                    <li>Xếp hạng lượt đọc</li>
                                    <li>Xếp hạng đề cử</li>
                                    <li>Xếp hạng tặng thưởng</li>
                                    <li>Xếp hạng bình luận</li>
                                </ul>
                            </li>
                            <li className={clsx(styles.menuItem)}>
                                <span className={clsx(styles.icon)}>⏱️</span> Thời
                                gian thực
                            </li>
                            <li className={clsx(styles.menuItem)}>
                                <span className={clsx(styles.icon)}>⭐</span> Đánh giá
                                mới
                            </li>
                        </ul>
                    </div>
                </div>
            )}
            <LoginModal 
                isOpen={isLoginModalOpen} 
                type={DEFAULT.LOGIN_TYPE.LOGIN}
                onClose={() => setIsLoginModalOpen(false)} 
            />
            <LoginModal 
                isOpen={isRegisterModalOpen} 
                type={DEFAULT.LOGIN_TYPE.REGISTER}
                onClose={() => setIsRegisterModalOpen(false)} 
            />
        </>
    );
};

export default Header;

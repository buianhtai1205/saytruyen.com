import React, { useState } from 'react';
import clsx from 'clsx';
import styles from './Header.module.scss';
import logo from '../../assets/images/logo150.png';
import { images } from '../../assets/svg';
import loginIcon from '../../assets/images/loginIcon.svg'; // Adjust the path as necessary

const Header = () => {
    const [isSearchVisible, setIsSearchVisible] = useState(false);
    const [isMenuVisible, setIsMenuVisible] = useState(false);

    const toggleSearch = () => {
        setIsSearchVisible(!isSearchVisible);
    };

    const toggleMenu = () => {
        setIsMenuVisible(!isMenuVisible);
    };

    const toggleTheme = () => {
        // Theme toggle logic here
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
                        <div>
                            <div
                                className={styles.toggleButton}
                                onClick={toggleTheme}
                            >
                                <span>🌙</span>
                            </div>
                            <div
                                className={styles.closeButton}
                                onClick={toggleMenu}
                            >
                                <span>X</span>
                            </div>
                        </div>
                        <ul>
                            <li className={styles.menuItem}>
                                <span className={styles.icon}>🗝️</span> Đăng
                                nhập
                            </li>
                            <li className={styles.menuItem}>
                                <span className={styles.icon}>📝</span> Đăng ký
                                tài khoản
                            </li>
                            <li className={styles.menuItem}>
                                <span className={styles.icon}>📚</span> Đăng
                                truyện
                            </li>
                            <li className={styles.menuItem}>
                                <span className={styles.icon}>📖</span> Kho
                                truyện
                                <ul>
                                    <li>Truyện mới</li>
                                    <li>Truyện full</li>
                                </ul>
                            </li>
                            <li className={styles.menuItem}>
                                <span className={styles.icon}>🏆</span> Xếp hạng
                                <ul>
                                    <li>Xếp hạng lượt đọc</li>
                                    <li>Xếp hạng đề cử</li>
                                    <li>Xếp hạng tặng thưởng</li>
                                    <li>Xếp hạng bình luận</li>
                                </ul>
                            </li>
                            <li className={styles.menuItem}>
                                <span className={styles.icon}>⏱️</span> Thời
                                gian thực
                            </li>
                            <li className={styles.menuItem}>
                                <span className={styles.icon}>⭐</span> Đánh giá
                                mới
                            </li>
                        </ul>
                    </div>
                </div>
            )}
        </>
    );
};

export default Header;

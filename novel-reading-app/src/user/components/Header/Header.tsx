import React, { useState } from 'react';
import clsx from 'clsx';
import styles from './Header.module.scss';
import logo from '../../assets/images/logo150.png';
import { images } from '../../assets/svg';

const Header = () => {
    const [isSearchVisible, setIsSearchVisible] = useState(false);

    const toggleSearch = () => {
        setIsSearchVisible(!isSearchVisible);
    };

    return (
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
                <img
                    src={images.menuIcon}
                    alt="menu"
                    className={clsx(styles.menuIcon)}
                />
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
    );
};

export default Header;

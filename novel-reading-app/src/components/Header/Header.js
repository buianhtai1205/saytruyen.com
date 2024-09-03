import React from 'react';
import clsx from 'clsx';
import styles from './Header.module.scss';
import logo from '../../assets/images/logo150.png';
import { images } from '../../assets/svg';

const Header = () => (
    <header className={clsx(styles.header)}>
        <div>
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
    </header>
);

export default Header;

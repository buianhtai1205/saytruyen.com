import React, { useEffect, useState } from 'react';
import clsx from 'clsx';
import styles from './GoToTop.module.scss';

const GoToTop: React.FC = () => {
    const [showTopBtn, setShowTopBtn] = useState(false);

    useEffect(() => {
        const handleScroll = () => {
            if (window.scrollY > 400) {
                setShowTopBtn(true);
            } else {
                setShowTopBtn(false);
            }
        };

        window.addEventListener('scroll', handleScroll);
        return () => window.removeEventListener('scroll', handleScroll);
    }, []);

    const goToTop = () => {
        window.scrollTo({
            top: 0,
            behavior: 'smooth',
        });
    };

    return showTopBtn ? (
        <button
            className={clsx(styles.goToTop)}
            onClick={goToTop}
            aria-label="Go to top"
        >
            ↑
        </button>
    ) : null;
};

export default GoToTop;

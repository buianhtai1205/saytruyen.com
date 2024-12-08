import React, { ButtonHTMLAttributes } from 'react';
import clsx from 'clsx';
import styles from './CustomButton.module.scss';

interface CustomButtonProps extends ButtonHTMLAttributes<HTMLButtonElement> {
    variant?:
        | 'RoyalBlue'
        | 'VividPurple'
        | 'SkyBlue'
        | 'Aquamarine'
        | 'Crimson'
        | 'Lavender'
        | 'Orange'
        | 'Lilac'
        | 'ElectricPurple'
        | 'HotPink'
        | 'Azure'
        | 'Turquoise'
        | 'Goldenrod'
        | 'Violet'
        | 'GhostWhite'
        | 'Slate';
    size?: 'sm' | 'md' | 'lg';
    outline?: boolean;
}

const CustomButton: React.FC<CustomButtonProps> = ({
    children,
    variant = 'RoyalBlue',
    size = 'md',
    outline = false,
    className,
    ...props
}) => {
    const buttonClasses = [
        clsx(styles.btn),
        clsx(styles[`btn${variant}`]),
        clsx(styles[`btn${size}`]),
        outline ? clsx(styles[`btnOutline${variant}`]) : '',
        className,
    ]
        .filter(Boolean)
        .join(' ');

    return (
        <div className={clsx(styles.container)}>
            <button className={buttonClasses} {...props}>
                {[
                    'SkyBlue',
                    'Aquamarine',
                    'Crimson',
                    'Lavender',
                    'Orange',
                    'Lilac',
                ].includes(variant) ? (
                    <span>{children}</span>
                ) : variant === 'Turquoise' ? (
                    <>
                        <span>Click!</span>
                        <span>{children}</span>
                    </>
                ) : variant === 'Azure' ? (
                    <>
                        {children}
                        <div className={clsx(styles.dot)}></div>
                    </>
                ) : (
                    children
                )}
            </button>
        </div>
    );
};

export default CustomButton;

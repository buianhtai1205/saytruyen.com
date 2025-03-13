import React from 'react';
import clsx from 'clsx';
import CustomButton from '@userComponents/CustomButton/CustomButton';
import styles from './CommonComponent.module.scss';

const CommonComponent = () => {
    return (
        <div className={clsx(styles.container)}>
            <h1>CommonComponent</h1>
            <CustomButton variant="RoyalBlue">Read More</CustomButton>
            <CustomButton variant="VividPurple">Read More</CustomButton>
            <CustomButton variant="SkyBlue">Read More</CustomButton>
            <CustomButton variant="Aquamarine">Read More</CustomButton>
            <CustomButton variant="Crimson">Read More</CustomButton>
            <CustomButton variant="Lavender">Read More</CustomButton>
            <CustomButton variant="Orange">Read More</CustomButton>
            <CustomButton variant="Lilac">Read More</CustomButton>
            <CustomButton variant="ElectricPurple">Read More</CustomButton>
            <CustomButton variant="HotPink">Read More</CustomButton>
            <CustomButton variant="Azure">Read More</CustomButton>
            <CustomButton variant="Turquoise">Read More</CustomButton>
            <CustomButton variant="Goldenrod">Read More</CustomButton>
            <CustomButton variant="Violet">Read More</CustomButton>
            <CustomButton variant="GhostWhite">Read More</CustomButton>
            <CustomButton variant="Slate">Read More</CustomButton>
        </div>
    );
};

export default CommonComponent;

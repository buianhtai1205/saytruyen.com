import { ReactNode } from 'react';
import './GlobalStyles.scss';

interface GlobalStylesProps {
    children: ReactNode;
}

const GlobalStyles = ({ children }: GlobalStylesProps) => {
    return <div>{children}</div>;
};

export default GlobalStyles;

import { ReactNode } from 'react';
import './GlobalStyles.scss';

interface GlobalStylesProps {
    children: ReactNode;
}

const GlobalStyles = ({ children }: GlobalStylesProps) => {
    return (
        <div className="appUser">
            <div className="container">{children}</div>
        </div>
    )
};

export default GlobalStyles;

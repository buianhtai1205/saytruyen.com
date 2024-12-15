import React, { useState, createContext, ReactNode } from 'react';

interface MenuContextProps {
    activeMenu: string;
    setActiveMenu: React.Dispatch<React.SetStateAction<string>>;
}

interface ChildContainerProps {
    children: ReactNode;
}

export const MenuContext = createContext<MenuContextProps>({
    activeMenu: '',
    setActiveMenu: () => {},
});

export const MenuProvider: React.FC<ChildContainerProps> = ({ children }) => {
    const [activeMenu, setActiveMenu] = useState<string>('');

    const value = {
        activeMenu,
        setActiveMenu,
    };

    return (
        <MenuContext.Provider value={value}>{children}</MenuContext.Provider>
    );
};

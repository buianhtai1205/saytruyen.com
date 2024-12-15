import React, { useEffect, useContext } from 'react';
import { Ripple } from 'primereact/ripple';
import { classNames } from 'primereact/utils';
import { CSSTransition } from 'react-transition-group';
import { MenuContext } from './context/MenuContext';
import { AppMenuItem, AppMenuItemProps } from './types';

const AppMenuitem: React.FC<AppMenuItemProps> = (props) => {
    const { activeMenu, setActiveMenu } = useContext(MenuContext);
    const item = props.item as AppMenuItem;
    const key = props.parentKey
        ? `${props.parentKey}-${props.index}`
        : String(props.index);
    const isActiveRoute = item?.to && window.location.pathname === item.to;
    const active = activeMenu === key || activeMenu.startsWith(`${key}-`);

    const onRouteChange = (url: string) => {
        if (item?.to && item.to === url) {
            setActiveMenu(key);
        }
    };

    useEffect(() => {
        onRouteChange(window.location.pathname);
    }, []);

    const itemClick = (
        event: React.MouseEvent<HTMLAnchorElement, MouseEvent>
    ) => {
        if (item?.disabled) {
            event.preventDefault();
            return;
        }

        if (item?.command) {
            item.command({ originalEvent: event, item });
        }

        if (item?.items) {
            setActiveMenu(active ? (props.parentKey as string) : key);
        } else {
            setActiveMenu(key);
        }
    };

    const subMenu = item?.items && item.visible !== false && (
        <CSSTransition
            timeout={{ enter: 1000, exit: 450 }}
            classNames="layout-submenu"
            in={props.root ? true : active}
            key={item.label}
        >
            <ul>
                {item.items.map((child, i) => (
                    <AppMenuitem
                        item={child}
                        index={i}
                        className={child.badgeClass}
                        parentKey={key}
                        key={child.label}
                    />
                ))}
            </ul>
        </CSSTransition>
    );

    return (
        <li
            className={classNames({
                'layout-root-menuitem': props.root,
                'active-menuitem': active,
            })}
        >
            {props.root && item?.visible !== false && (
                <div className="layout-menuitem-root-text">{item.label}</div>
            )}
            {(!item?.to || item?.items) && item?.visible !== false ? (
                <a
                    href={item.url}
                    onClick={(e) => itemClick(e)}
                    className={classNames(item.class, 'p-ripple')}
                    target={item.target}
                    tabIndex={0}
                >
                    <i
                        className={classNames(
                            'layout-menuitem-icon',
                            item.icon
                        )}
                    ></i>
                    <span className="layout-menuitem-text">{item.label}</span>
                    {item.items && (
                        <i className="pi pi-fw pi-angle-down layout-submenu-toggler"></i>
                    )}
                    <Ripple />
                </a>
            ) : null}
            {item?.to && !item?.items && item?.visible !== false ? (
                <a
                    href={item.to}
                    onClick={(e) => {
                        e.preventDefault();
                        window.location.href = item.to ? item.to : '';
                        itemClick(e);
                    }}
                    className={classNames(item.class, 'p-ripple', {
                        'active-route': isActiveRoute,
                    })}
                    target={item.target}
                    tabIndex={0}
                >
                    <i
                        className={classNames(
                            'layout-menuitem-icon',
                            item.icon
                        )}
                    ></i>
                    <span className="layout-menuitem-text">{item.label}</span>
                    {item.items && (
                        <i className="pi pi-fw pi-angle-down layout-submenu-toggler"></i>
                    )}
                    <Ripple />
                </a>
            ) : null}
            {subMenu}
        </li>
    );
};

export default AppMenuitem;

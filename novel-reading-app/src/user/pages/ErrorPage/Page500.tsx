import React from 'react';
import { Link } from 'react-router-dom';
import clsx from 'clsx';
import styles from './Page404.module.scss';

const Page500: React.FC = () => {
    return (
        <div className={styles.container}>
            <div className={clsx(styles.title)}>
                HTTP: <span className={clsx(styles.number)}>500</span>
            </div>
            <div className={clsx(styles.codeBlock)}>
                <div className={clsx(styles.codeLine)}>
                    <span className={clsx(styles.yellow)}>server</span>
                    <span className={clsx(styles.operator)}>.</span>
                    <span className={clsx(styles.blue)}>internal_error</span>
                    <span className={clsx(styles.white)}> = </span>
                    <span className={clsx(styles.yellow)}>true</span>
                    <span className={clsx(styles.white)}>;</span>
                </div>

                <div className={clsx(styles.codeLine)}>
                    <span className={clsx(styles.purple)}>try </span>
                    <span className={clsx(styles.white)}>{`{`}</span>
                    <span className={clsx(styles.blue)}>fix_server</span>
                    <span className={clsx(styles.yellow)}>()</span>
                    <span className={clsx(styles.white)}>{`}`}</span>
                </div>

                <div className={clsx(styles.codeLine)}>
                    <span className={clsx(styles.purple)}>catch </span>
                    <span className={clsx(styles.white)}>(error) {`{`}</span>
                </div>

                <div className={clsx(styles.codeLine)}>
                    <span className={clsx(styles.white)}>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                    </span>
                    <span className={clsx(styles.blue)}>console</span>
                    <span className={clsx(styles.operator)}>.</span>
                    <span className={clsx(styles.blue)}>error</span>
                    <span className={clsx(styles.white)}>(</span>
                    <span className={clsx(styles.green)}>
                        "Something went terribly wrong..."
                    </span>
                    <span className={clsx(styles.white)}>);</span>
                </div>

                <div className={clsx(styles.codeLine)}>
                    <span className={clsx(styles.white)}>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                    </span>
                    <span className={clsx(styles.blue)}>notify_developers</span>
                    <span className={clsx(styles.white)}>(</span>
                    <span className={clsx(styles.green)}>
                        "Wake up! Production is down!"
                    </span>
                    <span className={clsx(styles.white)}>);</span>
                </div>

                <div className={clsx(styles.codeLine)}>
                    <span className={clsx(styles.white)}>{`}`}</span>
                </div>
            </div>
            <Link to="/" className={clsx(styles.homeLink)}>
                HOME
            </Link>
        </div>
    );
};

export default Page500;

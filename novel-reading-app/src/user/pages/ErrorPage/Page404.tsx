import React from 'react';
import { Link } from 'react-router-dom';
import clsx from 'clsx';
import styles from './Page404.module.scss';

const Page404: React.FC = () => {
    return (
        <div className={clsx(styles.container)}>
            <div className={clsx(styles.title)}>
                HTTP: <span className={clsx(styles.number)}>404</span>
            </div>

            <div className={clsx(styles.codeBlock)}>
                <div className={clsx(styles.codeLine)}>
                    <span className={clsx(styles.yellow)}>this_page</span>
                    <span className={clsx(styles.operator)}>.</span>
                    <span className={clsx(styles.blue)}>not_found</span>
                    <span className={clsx(styles.white)}> = </span>
                    <span className={clsx(styles.operator)}>true</span>
                    <span className={clsx(styles.white)}>;</span>
                </div>

                <div className={clsx(styles.codeLine)}>
                    <span className={clsx(styles.purple)}>if </span>
                    <span className={clsx(styles.yellow)}>
                        (you_spelt_it_wrong){' '}
                    </span>
                    <span className={clsx(styles.white)}>{`{`}</span>
                    <span className={clsx(styles.blue)}>try_again</span>
                    <span className={clsx(styles.yellow)}>()</span>
                    <span className={clsx(styles.white)}>{`};`}</span>
                </div>

                <div className={clsx(styles.codeLine)}>
                    <span className={clsx(styles.purple)}>else if </span>
                    <span className={clsx(styles.yellow)}>
                        (we_screwed_up){' '}
                    </span>
                    <span className={clsx(styles.white)}>{`{`}</span>
                    <span className={clsx(styles.blue)}>alert</span>
                    <span className={clsx(styles.white)}>(</span>
                    <span className={clsx(styles.green)}>
                        "We're really sorry about that."
                    </span>
                    <span className={clsx(styles.white)}>); </span>
                    <span className={clsx(styles.yellow)}>window</span>
                    <span className={clsx(styles.operator)}>.</span>
                    <span className={clsx(styles.blue)}>location</span>
                    <span className={clsx(styles.white)}> = </span>
                    <span className={clsx(styles.blue)}>home</span>
                    <span className={clsx(styles.white)}>{`};`}</span>
                </div>
            </div>
            <Link to="/" className={clsx(styles.homeLink)}>
                HOME
            </Link>
        </div>
    );
};

export default Page404;

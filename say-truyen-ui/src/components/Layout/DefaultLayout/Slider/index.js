import { useState } from "react";
import clsx from "clsx";
import {
    faArrowLeft,
    faArrowRight
} from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

import styles from "./Slider.module.scss";

const initialItems = [
    {
        imageUrl: 'https://i.ibb.co/qCkd9jS/img1.jpg',
        name: 'Switzerland',
        description: 'Lorem ipsum dolor, sit amet consectetur adipisicing elit. Ab, eum!',
    },
    {
        imageUrl: 'https://i.ibb.co/jrRb11q/img2.jpg',
        name: 'Finland',
        description: 'Lorem ipsum dolor, sit amet consectetur adipisicing elit. Ab, eum!',
    },
    {
        imageUrl: 'https://i.ibb.co/NSwVv8D/img3.jpg',
        name: 'Iceland',
        description: 'Lorem ipsum dolor, sit amet consectetur adipisicing elit. Ab, eum!',
    },
    {
        imageUrl: 'https://i.ibb.co/Bq4Q0M8/img4.jpg',
        name: 'Australia',
        description: 'Lorem ipsum dolor, sit amet consectetur adipisicing elit. Ab, eum!',
    },
    {
        imageUrl: 'https://i.ibb.co/jTQfmTq/img5.jpg',
        name: 'Netherland',
        description: 'Lorem ipsum dolor, sit amet consectetur adipisicing elit. Ab, eum!',
    },
    {
        imageUrl: 'https://i.ibb.co/RNkk6L0/img6.jpg',
        name: 'Ireland',
        description: 'Lorem ipsum dolor, sit amet consectetur adipisicing elit. Ab, eum!',
    },
];

const Slider = () => {

    const [items, setItems] = useState(initialItems);

    const handleNext = () => {
        setItems(prevItems => {
            const newItems = [...prevItems];
            const firstItem = newItems.shift();
            newItems.push(firstItem);
            return newItems;
        });
    };

    const handlePrev = () => {
        setItems(prevItems => {
            const newItems = [...prevItems];
            const lastItem = newItems.pop();
            newItems.unshift(lastItem);
            return newItems;
        });
    };


    return (
        <div class={clsx(styles.container)}>
            <div className={clsx(styles.slide)}>
                {items.map((item, index) => (
                    <div
                        key={index}
                        className={clsx(styles.item)}
                        style={{ backgroundImage: `url(${item.imageUrl})` }}
                    >
                        <div className={clsx(styles.content)}>
                            <div className={clsx(styles.name)}>{item.name}</div>
                            <div className={clsx(styles.des)}>{item.description}</div>
                            <button>See More</button>
                        </div>
                    </div>
                ))}
            </div>

            <div class={clsx(styles.button)}>
                <button class={clsx(styles.prev)} onClick={handlePrev}>
                    <FontAwesomeIcon icon={faArrowLeft} ></FontAwesomeIcon>
                </button>
                <button class={clsx(styles.next)} onClick={handleNext}>
                    <FontAwesomeIcon icon={faArrowRight} ></FontAwesomeIcon>
                </button>
            </div >

        </div >
    )
};

export default Slider;
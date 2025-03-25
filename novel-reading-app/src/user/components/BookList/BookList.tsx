import React, { useEffect, useState } from 'react';
import clsx from 'clsx';
import styles from './BookList.module.scss';
import {
    fetchRandomStories,
    StoryResponse,
} from '../../../api/services/story-service/storyService';

const BookList: React.FC = () => {
    const [stories, setStories] = useState<Array<StoryResponse>>();
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        const loadStories = async () => {
            try {
                const data = await fetchRandomStories();
                setStories(data.data);
            } catch (err) {
                setError('Unable to fetch stories. Please try again later.');
            }
        };
        loadStories();
    }, []);

    if (error) return <div>{error}</div>;

    return (
        <div className={clsx(styles.bookList)}>
            <h2 className={clsx(styles.title)}>BTV ĐỀ CỬ</h2>
            <div className={clsx(styles.books)}>
                {stories &&
                    stories.map((story) => (
                        <a
                            href={`/truyen/${story.slug}-${story.id}`}
                            className={clsx(styles.bookLink)}
                            target="_blank"
                            key={story.id}
                        >
                            <div className={clsx(styles.bookCard)}>
                                <div className={clsx(styles.imageWrapper)}>
                                    <img
                                        src={story.poster[1]}
                                        alt={story.name}
                                        className={clsx(styles.bookImage)}
                                    />
                                </div>
                                <div className={clsx(styles.bookInfo)}>
                                    <h3 className={clsx(styles.bookTitle)}>
                                        {story.name}
                                    </h3>
                                    <p className={clsx(styles.bookDescription)}>
                                        {story.description}
                                    </p>
                                    <div className={clsx(styles.bookMeta)}>
                                        <span className={clsx(styles.author)}>
                                            {story.authorId}
                                        </span>
                                        <span
                                            className={clsx(styles.genreButton)}
                                        >
                                            {story.kind}
                                        </span>
                                    </div>
                                </div>
                            </div>
                        </a>
                    ))}
            </div>
        </div>
    );
};

export default BookList;

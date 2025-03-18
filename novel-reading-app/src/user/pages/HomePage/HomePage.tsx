import React, { useEffect, useState } from 'react';
import Banner from '@userComponents/Banner/Banner';
import BookList from '@userComponents/BookList/BookList';
import Top from '@userComponents/Top/Top';
import NewChapters from '@userComponents/NewChapters/NewChapters';
import NewCompletedStory from '@userComponents/NewCompletedStory/NewCompletedStory';
import Evaluation from '@userComponents/Evaluation/Evaluation';
import {
    fetchRandomBanners,
    BannerResponse,
} from '@api/services/story-service/bannerService';

const HomePage: React.FC = () => {
    const [banners, setBanners] = useState<Array<BannerResponse>>();
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        const loadBanners = async () => {
            try {
                const data = await fetchRandomBanners();
                setBanners(data.data);
            } catch (err) {
                setError('Unable to fetch Banners. Please try again later.');
            }
        };

        loadBanners();
    }, []);

    if (error) return <div>{error}</div>;

    return (
        <>
            <Banner
                imageUrl={banners?.[0]?.bannerDesktop || ''}
                linkUrl={
                    banners?.[0]?.url + '-' + banners?.[0]?.bannerLinkedId || ''
                }
                altText={banners?.[0]?.name || ''}
            />
            <BookList />
            <Banner
                imageUrl={banners?.[1]?.bannerDesktop || ''}
                linkUrl={
                    banners?.[1]?.url + '-' + banners?.[1]?.bannerLinkedId || ''
                }
                altText={banners?.[1]?.name || ''}
            />
            <Top />
            <NewChapters />
            <Banner
                imageUrl={banners?.[2]?.bannerDesktop || ''}
                linkUrl={
                    banners?.[2]?.url + '-' + banners?.[2]?.bannerLinkedId || ''
                }
                altText={banners?.[2]?.name || ''}
            />
            <NewCompletedStory />
            <Evaluation />
        </>
    );
};

export default HomePage;

import React, { useEffect, useState } from 'react';
import Banner from '@userComponents/Banner/Banner';
import BookList from '@userComponents/BookList/BookList';
import Top from '@userComponents/Top/Top';
import NewChapters from '@userComponents/NewChapters/NewChapters';
import NewCompletedStory from '@userComponents/NewCompletedStory/NewCompletedStory';
import Evaluation from '@userComponents/Evaluation/Evaluation';
import { PageableResponse } from '@api/common/pageableResponse';
import {
    fetchBanners,
    BannerResponse,
} from '@api/services/story-service/bannerService';

const HomePage: React.FC = () => {
    const [banners, setBanners] = useState<PageableResponse<BannerResponse>>();
    const [error, setError] = useState<string | null>(null);

    const loadBanners = async () => {
        try {
            const data = await fetchBanners();
            setBanners(data.data);
        } catch (err) {
            setError('Unable to fetch Banners. Please try again later.');
        }
    };

    useEffect(() => {
        loadBanners();
    }, []);

    if (error) return <div>{error}</div>;

    return (
        <>
            <Banner
                imageUrl={banners?.data?.[0]?.bannerDesktop || ''}
                linkUrl={
                    banners?.data?.[0]?.url +
                        '-' +
                        banners?.data?.[0]?.bannerLinkedId || ''
                }
                altText={banners?.data?.[0]?.name || ''}
            />
            <BookList />
            <Banner
                imageUrl={banners?.data?.[1]?.bannerDesktop || ''}
                linkUrl={
                    banners?.data?.[1]?.url +
                        '-' +
                        banners?.data?.[1]?.bannerLinkedId || ''
                }
                altText={banners?.data?.[1]?.name || ''}
            />
            <Top />
            <NewChapters />
            <Banner
                imageUrl={banners?.data?.[2]?.bannerDesktop || ''}
                linkUrl={
                    banners?.data?.[2]?.url +
                        '-' +
                        banners?.data?.[2]?.bannerLinkedId || ''
                }
                altText={banners?.data?.[2]?.name || ''}
            />
            <NewCompletedStory />
            <Evaluation />
        </>
    );
};

export default HomePage;

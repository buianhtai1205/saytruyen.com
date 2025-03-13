import React, { useEffect, useState } from 'react';
import Banner from '@userComponents/Banner/Banner';
import { PageableResponse } from '@api/common/pageableResponse';
import {
    fetchBanners,
    BannerResponse,
} from '@api/services/story-service/bannerService';
import ChapterContent from '@userComponents/ChapterContent/ChapterContent';
import ChapterNavigation from '@userComponents/ChapterNavigation/ChapterNavigation';
import StoryComment from '@userComponents/StoryComment/StoryComment';

const ChapterDetail: React.FC = () => {
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

    // if (error) return <div>{error}</div>;

    return (
        <>
            {/* <Banner
                imageUrl={banners?.data?.[0]?.bannerDesktop || ''}
                linkUrl={
                    banners?.data?.[0]?.url +
                    '-' +
                    banners?.data?.[0]?.bannerLinkedId || ''
                }
                altText={banners?.data?.[0]?.name || ''}
            /> */}
            <Banner
                imageUrl="https://static.cdnno.com/storage/topbox/6c32fa95ee921c0de552f3c6aed10ecc.webp"
                linkUrl="#"
                altText="img"
            />
            <ChapterContent />
            <Banner
                imageUrl="https://static.cdnno.com/storage/topbox/6c32fa95ee921c0de552f3c6aed10ecc.webp"
                linkUrl="#"
                altText="img"
            />
            <ChapterNavigation />
            <StoryComment />
        </>
    );
};
export default ChapterDetail;

import React, { useState } from 'react';
import clsx from 'clsx';
import styles from './ChapterContent.module.scss';
import GoToTop from '@userComponents/GoToTop/GoToTop';
import { StoryResponse } from '@api/services/story-service/storyService';
import { ChapterResponse } from '@api/services/story-service/chapterService';
import { ApiResponse } from '@api/common/apiResponse';
import ListChapter from '@userComponents/ListChapter/ListChapter';

interface ChapterContentProps {
    storyInfo: StoryResponse,
    chapterInfo: ChapterResponse,
    chapterData: {
        chapterList: ApiResponse<Array<ChapterResponse>> | undefined,
        onFetchChapterList: () => void
    }
}

const ChapterContent: React.FC<ChapterContentProps> = ({ storyInfo, chapterInfo, chapterData }) => {
    const [isListVisible, setIsListVisible] = useState(false);

    const handleToggleList = () => {
        console.log("onclcik");
        chapterData.onFetchChapterList();
        setIsListVisible(!isListVisible);
    };

    return (
        <div className={clsx(styles.chapterContentContainer)}>
            <header className={clsx(styles.chapterHeader)}>
                <h1 className={clsx(styles.novelTitle)}>
                    {storyInfo.name}
                </h1>
                <div className={clsx(styles.author)}>
                    {storyInfo.authorId}
                </div>

                <div className={clsx(styles.navigation)}>
                    <button className={clsx(styles.navButton)}>
                        <span className={clsx(styles.navArrow)}>&#9664;</span>
                    </button>
                    <div className={clsx(styles.chapterInfo)}>
                        {chapterInfo.name}
                    </div>
                    <button className={clsx(styles.navButton)}>
                        <span className={clsx(styles.navArrow)}>&#9654;</span>
                    </button>
                </div>

                <div className={clsx(styles.toolbar)}>
                    <button className={clsx(styles.toolbarButton)}>
                        <span className={clsx(styles.iconGear)}>&#9881;</span>{' '}
                        Cấu hình
                    </button>
                    <button className={clsx(styles.toolbarButton)} onClick={handleToggleList}>
                        <span className={clsx(styles.iconList)}>&#9776;</span>{' '}
                        Mục lục
                    </button>
                    <button className={clsx(styles.toolbarButton)}>
                        <span className={clsx(styles.iconBookmark)}>
                            &#9733;
                        </span>{' '}
                        Đánh dấu
                    </button>
                </div>
            </header>

            <main className={clsx(styles.chapterBody)}>
                {chapterInfo.content ? (
                    <p
                        className={clsx(styles.paragraph)}
                        dangerouslySetInnerHTML={{ __html: chapterInfo.content }}
                    />
                ) : (
                    <>
                        <p className={clsx(styles.paragraph)}>
                            "Đại Đạo sinh ra tại Hỗn Độn, tự đại đạo sinh ra
                            lên, vĩ diện vũ trụ không ngừng tăng sinh, mở rộng,
                            Thiên Đình khi nào sinh ra, không người biết được,
                            Thiên Đế là sống được cổ xưa nhất tồn tại một trong,
                            tại hắn đương quyền giai đoạn, ba ngàn đại thế giới
                            trải qua phá diệt, đúc lại, nói cách khác, ba ngàn
                            đại thế giới thời gian tồn tại cũng không bằng Thiên
                            Đế sống được lâu."
                        </p>

                    </>
                )}
                <p className={clsx(styles.paragraph)}>
                    ---
                </p>
                <p className={clsx(styles.paragraph)}>
                    Vô Lượng Đại Hư Kiếm Tổ nhìn xem trên bàn ấm trà,
                    ngữ khí chậm rãi nói, mà Cố An nghiêm túc nghe.
                </p>
                <p className={clsx(styles.paragraph)}>
                    Xuyên việt tọa ủng mười tỉ tài sản, bắt đầu một cái
                    gọi cảm quyến rũ thê tử, bảy cái phong hoa tuyệt đại
                    nữ nhi. Nhưng mà Hứa Hạo lại hoảng sợ được một nhóm.
                    Bởi vì thân phận của hắn là đại phản phái, kết cục
                    là nghịch tử nhân vật chính trở về, giết hắn chiếm
                    lấy thê nữ. Cũng may còn có thời gian hai năm rưỡi,
                    Hứa Hạo có thể trước giờ bỏ cục.
                </p>
                <p className={clsx(styles.paragraph)}>
                    Mời đọc
                    <span className={clsx(styles.paragraph, styles.referStory)}>
                        Trước Giờ Xuyên Thành Phản Phái,
                        Bức Nhân Vật Chính Mụ Mụ Sinh Hài Tử
                    </span>

                </p>
            </main>

            <GoToTop />
            {isListVisible 
                && <ListChapter 
                    chapterListProps={chapterData.chapterList} 
                    parentIsModalOpen={true}
                />}
        </div>
    );
};

export default ChapterContent;

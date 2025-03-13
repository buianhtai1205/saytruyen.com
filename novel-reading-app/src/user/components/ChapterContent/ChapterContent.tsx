import React from 'react';
import clsx from 'clsx';
import styles from './ChapterContent.module.scss';
import GoToTop from '@userComponents/GoToTop/GoToTop';

const chapterContent = {
    title: 'Câu Thà Thành Thành Nhân, Tiên Quan Triệu Ta Châm Ngưa',
    author: 'Nham Nga Tiểu',
    chapterNumber: 701,
    chapterTitle: 'Thiên Đình truyện thuyết',
    content: [],
};

const ChapterContent: React.FC = () => {
    return (
        <div className={clsx(styles.chapterContentContainer)}>
            <header className={clsx(styles.chapterHeader)}>
                <h1 className={clsx(styles.novelTitle)}>
                    {chapterContent.title}
                </h1>
                <div className={clsx(styles.author)}>
                    {chapterContent.author}
                </div>

                <div className={clsx(styles.navigation)}>
                    <button className={clsx(styles.navButton)}>
                        <span className={clsx(styles.navArrow)}>&#9664;</span>
                    </button>
                    <div className={clsx(styles.chapterInfo)}>
                        Chương {chapterContent.chapterNumber}:{' '}
                        {chapterContent.chapterTitle}
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
                    <button className={clsx(styles.toolbarButton)}>
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
                <h2 className={clsx(styles.chapterHeading)}>
                    Chương {chapterContent.chapterNumber}:{' '}
                    {chapterContent.chapterTitle}
                </h2>

                {chapterContent.content.length > 0 ? (
                    chapterContent.content.map((paragraph, index) => (
                        <p key={index} className={clsx(styles.paragraph)}>
                            {paragraph}
                        </p>
                    ))
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
                            Hứa Hạo có thể trước giờ bỏ cục. Mời đọc Trước Giờ
                            Xuyên Thành Phản Phái, Bức Nhân Vật Chính Mụ Mụ Sinh
                            Hài Tử
                        </p>
                    </>
                )}
            </main>

            <GoToTop />
        </div>
    );
};

export default ChapterContent;

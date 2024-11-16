import React from 'react';
import clsx from 'clsx';
import styles from './StoryDescription.module.scss';

export default function StoryDescription() {
    return (
        <div className={clsx(styles.introduction)}>
            <div className={clsx(styles.header)}>GIỚI THIỆU</div>

            <div className={clsx(styles.content)}>
                <p>
                    « Bí ẩn manga tiểu thuyết đặc nhất vô nhị kỳ hợp đồng tiểu
                    thuyết: Võ Hiệp: Ở Tiểu Trấn Mở Tửu Quán, Nhật Thị Sư Phi
                    Huyền »
                </p>

                <p>Tiếng Võ thế giới, Chư Hùng cùng nổi lên.</p>

                <p>
                    Cổ Võ Thương Hoàng giả mưu toan Hoành Tảo Thiên Hạ, chiếm
                    đoạt các nước.
                </p>

                <p>
                    Cùng có Võ Thương Tông Sư say mê võ đạo, một lòng đăng lâm
                    đỉnh cao nhất.
                </p>

                <p>
                    Còn có trường sinh bất tử người ẩn dấu phía sau màn, điều
                    khiển thiên hạ.
                </p>

                <p>
                    Vương Mãnh xuyên việt mà đến, kích hoạt hệ thống, thu được
                    một nhà tiền là Tửu Tiên Cư tiểu tửu quán.
                </p>

                <p>
                    Tửu quán có một cái đặc thù quy định, bất kỳ người nào đến
                    tửu quán đều có thể khiêu chiến ba chiến Đoạn Hồn Tửu, khiêu
                    chiến thành công, thì có thể hưởng tửu quán đưa ra bất kỳ
                    yêu cầu gì.
                </p>

                <p>Võ luận bất kỳ yêu cầu gì, tửu quán đều có thể thỏa mãn!</p>

                <p className={clsx(styles.highlight)}>
                    Chiến thứ nhất đoạn tình!
                </p>
                <p className={clsx(styles.highlight)}>
                    Chiến thứ hai đoạn dục!
                </p>
                <p className={clsx(styles.highlight)}>
                    Chiến thứ ba mới là đoạn hồn!
                </p>

                <p>Mà khiêu chiến trả giá là thứ trân quý nhất</p>

                <p>
                    Mà khiêu chiến càng nhiều người, hệ thống giải tỏa thần kỳ
                    rượu thì càng nhiều.
                </p>

                <p className={clsx(styles.sectionTitle)}>
                    Huyền Băng Bích Hỏa Tửu!
                </p>
                <p className={clsx(styles.sectionTitle)}>Bách Quả Tửu!</p>
                <p className={clsx(styles.sectionTitle)}>Phương Huyết Tửu!</p>
                <p className={clsx(styles.sectionTitle)}>Long Cốt Tửu!</p>
                <p>. . .</p>

                <p>
                    Cổ sư tử Sư Phi Huyền vì tuy lâm Loan Loan khiêu chiến Đoạn
                    Hồn Tửu, say ngã ở Tửu Tiên Cư bị Vương Mãnh nhặt thì bất
                    đầu. . .
                </p>

                <p>Loan Loan, Tiểu Phong đám người theo nhau mà đến.</p>

                <p>
                    Theo trong tửu quán các loại thần kỳ rượu bị phát hiện. . .
                </p>
            </div>

            <div className={clsx(styles.footer)}>
                <p>
                    Thông báo: CÁC PUBLISHER ĐƯỢC PHÉP ĐĂNG TRUYỆN ĐÃ ĐƯỢC CUỐI
                    CHƯƠNG XEM NHIỀU TRUYỆN HAY KHÁC QVT Yurisa LÀM Ở{' '}
                    <a
                        href="https://metruyencv.com/bo-suu-tap/10001D"
                        className={clsx(styles.link)}
                    >
                        https://metruyencv.com/bo-suu-tap/10001D
                    </a>
                    (hoặc bấm vào tên Yurisa ở web, hay cũng đăng bởi Yurisa ở
                    app MTG - Mê Truyện Chữ)
                </p>
            </div>
        </div>
    );
}

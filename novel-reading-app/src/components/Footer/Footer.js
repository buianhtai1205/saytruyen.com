import React from 'react';
import clsx from 'clsx';
import styles from './Footer.module.scss';
import logo from '../../assets/images/logo150.png';

const Footer = () => {
    return (
        <footer className={clsx(styles.footer)}>
            <div className={clsx(styles.footerContent)}>
                <p>
                Mê Truyện Chữ là nền tảng mở trực tuyến, miễn phí đọc truyện chữ được đóng góp nội dung từ các tác giả viết truyện và các dịch giả convert, dịch truyện, rất nhiều truyện hay và nổi bật được cập nhật nhanh nhất với đủ các thể loại tiên hiệp, kiếm hiệp, huyền ảo ... 
                </p>
                <img src={logo} alt='logo' className={styles.logo} />
                <div className={clsx(styles.footerLinks)}>
                    <a href="/terms">Điều khoản dịch vụ</a>
                    <a href="/privacy">Chính sách bảo mật</a>
                    <a href="/copyright">Về bản quyền</a>
                    <a href="/help">Hướng dẫn sử dụng</a>
                </div>
            </div>
        </footer>
    );
}

export default Footer;
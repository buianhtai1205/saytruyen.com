import clsx from "clsx";
import { Link } from "react-router-dom";
import styles from "./Footer.module.scss";

const Footer = () => {
	return (
		<div className={clsx(styles.footer)}>
			<Link className={clsx(styles.item)} to="/About">
				About
			</Link>
			<Link className={clsx(styles.item)} to="/Newsrooms">
				Newsrooms
			</Link>
			<Link className={clsx(styles.item)} to="/Contact">
				Contact
			</Link>
			<Link className={clsx(styles.item)} to="/Careers">
				Careers
			</Link>
			<Link className={clsx(styles.item)} to="/ByteDance">
				ByteDance
			</Link>
			<Link className={clsx(styles.item)} to="/TiktokForGood">
				Tiktok For Good
			</Link>
			<Link className={clsx(styles.item)} to="/Advertise">
				Advertise
			</Link>
			<Link className={clsx(styles.item)} to="/Developers">
				Developers
			</Link>
			<Link className={clsx(styles.item)} to="/Transparency">
				Transparency
			</Link>
			<Link className={clsx(styles.item)} to="/TikTokRewards">
				TikTok Rewards
			</Link>
			<Link className={clsx(styles.item)} to="/TikTokBrowse">
				TikTok Browse
			</Link>
			<Link className={clsx(styles.item)} to="/TikTokEmbeds">
				TikTok Embeds
			</Link>
			<Link className={clsx(styles.item)} to="/Help">
				Help
			</Link>
			<Link className={clsx(styles.item)} to="/Safety">
				Safety
			</Link>
			<Link className={clsx(styles.item)} to="/Terms">
				Terms
			</Link>
			<Link className={clsx(styles.item)} to="/Privacy">
				Privacy
			</Link>
			<Link className={clsx(styles.item)} to="/CreatorPortal">
				Creator Portal
			</Link>
			<Link className={clsx(styles.item)} to="/CommunityGuidelines">
				Community Guidelines
			</Link>
			<span data-e2e="copyright" className={clsx(styles.item)}>
				© 2023 TikTok
			</span>
		</div>
	);
};

export default Footer;

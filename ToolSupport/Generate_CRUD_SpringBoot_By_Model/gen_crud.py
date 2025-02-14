#!/usr/bin/env python3

import os
import argparse
import sys
from jinja2 import Environment, FileSystemLoader

import read_model
import utils

# Cấu hình thư mục chứa các template
TEMPLATE_DIR = os.path.join(os.path.dirname(__file__), 'templates')
env = Environment(loader=FileSystemLoader(TEMPLATE_DIR), trim_blocks=True, lstrip_blocks=True)
env.filters['lower_first'] = utils.lower_first

def render_template(template_name, context, output_path):
    """
    Render template với context và ghi kết quả vào output_path.
    Nếu thư mục chứa output_path không tồn tại, sẽ tạo thư mục đó.
    """
    try:
        # Lấy thư mục chứa file output
        output_dir = os.path.dirname(output_path)
        if not os.path.exists(output_dir):
            os.makedirs(output_dir, exist_ok=True)
    except Exception as e:
        print(f"[Error] Tạo thư mục cho {output_path} thất bại: {e}")
        raise

    try:
        template = env.get_template(template_name)
    except Exception as e:
        print(f"[Error] Lấy template {template_name} thất bại: {e}")
        raise

    try:
        rendered = template.render(context)
    except Exception as e:
        print(f"[Error] Render template {template_name} thất bại: {e}")
        raise

    try:
        with open(output_path, 'w', encoding='utf-8') as f:
            f.write(rendered)
        print(f"Đã tạo file: {output_path}")
    except Exception as e:
        print(f"[Error] Ghi file {output_path} thất bại: {e}")
        raise

if __name__ == "__main__":
    # Parse argument
    try:
        parser = argparse.ArgumentParser(
            description=("Generate code từ file model entity.\n"
                         "Ví dụ:\n"
                         "  python3 generate.py -model Banner.java -orm Jpa\n"
                         "  python3 generate.py -model Banner.java -orm Mongo\n\n"
                         "  -model: Đường dẫn đến file model entity (ví dụ: Banner.java).\n"
                         "  -orm  : Loại ORM sử dụng, Jpa hoặc Mongo (mặc định: Jpa).")
        )
        parser.add_argument(
            "-model",
            required=False,
            default="Bag.java",
            help="Đường dẫn đến file model entity (ví dụ: Banner.java)"
        )
        parser.add_argument(
            "-orm",
            required=False,
            default="Jpa",
            help="Loại ORM sử dụng: Jpa hoặc Mongo (mặc định: Jpa)"
        )
        args = parser.parse_args()
    except Exception as e:
        print(f"[Error] Lỗi khi parse argument: {e}")
        sys.exit(1)

    # Xử lý đường dẫn model
    try:
        model_path = args.model
        if not os.path.isabs(model_path):
            model_path = os.path.join(os.getcwd(), model_path)
    except Exception as e:
        print(f"[Error] Lỗi xử lý đường dẫn model: {e}")
        sys.exit(1)

    # Parse file model để lấy context
    try:
        context = read_model.parse_model_file(model_path)
        context["orm_type"] = args.orm
    except Exception as e:
        print(f"[Error] Lỗi khi parse file model {model_path}: {e}")
        sys.exit(1)

    # Tính toán root path (thư mục cha của thư mục chứa file model)
    try:
        root_path = os.path.dirname(os.path.dirname(model_path))
    except Exception as e:
        print(f"[Error] Lỗi khi xác định root path từ {model_path}: {e}")
        sys.exit(1)

    # Tạo các file từ template, bắt lỗi từng bước

    try:
        render_template(
            template_name="controller_interface_template.java",
            context=context,
            output_path=f"{root_path}/controller/{context['entity_name']}Controller.java"
        )
    except Exception as e:
        print(f"[Error] Lỗi tạo Controller Interface: {e}")

    try:
        render_template(
            template_name="controller_template.java",
            context=context,
            output_path=f"{root_path}/controller/impl/{context['entity_name']}ControllerImpl.java"
        )
    except Exception as e:
        print(f"[Error] Lỗi tạo Controller Implementation: {e}")

    try:
        render_template(
            template_name="request_template.java",
            context=context,
            output_path=f"{root_path}/request/{context['entity_name']}Request.java"
        )
    except Exception as e:
        print(f"[Error] Lỗi tạo Request: {e}")

    try:
        render_template(
            template_name="response_template.java",
            context=context,
            output_path=f"{root_path}/response/{context['entity_name']}Response.java"
        )
    except Exception as e:
        print(f"[Error] Lỗi tạo Response: {e}")

    try:
        render_template(
            template_name="converter_template.java",
            context=context,
            output_path=f"{root_path}/converter/{context['entity_name']}Converter.java"
        )
    except Exception as e:
        print(f"[Error] Lỗi tạo Converter: {e}")

    try:
        render_template(
            template_name="service_interface_template.java",
            context=context,
            output_path=f"{root_path}/service/{context['entity_name']}Service.java"
        )
    except Exception as e:
        print(f"[Error] Lỗi tạo Service Interface: {e}")

    try:
        render_template(
            template_name="service_template.java",
            context=context,
            output_path=f"{root_path}/service/impl/{context['entity_name']}ServiceImpl.java"
        )
    except Exception as e:
        print(f"[Error] Lỗi tạo Service Implementation: {e}")

    try:
        render_template(
            template_name="repository_interface_template.java",
            context=context,
            output_path=f"{root_path}/repository/{context['entity_name']}Repository.java"
        )
    except Exception as e:
        print(f"[Error] Lỗi tạo Repository: {e}")

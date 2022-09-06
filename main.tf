provider "aws"  {
  region = "eu-central-1"
}

terraform {
  required_providers {
    aws = {
      source = "hashicorp/aws"
      version = "~> 3.0"
    }
  }
  backend "s3" {
    bucket = "springboot-todo-bucket"
    key = "platform.tfstate"
    region = "eu-central-1"
  }
}

resource "aws_instance" "ubuntu" {
  ami = "ami-065deacbcaac64cf2"
  instance_type = "t2.micro"
  subnet_id = "subnet-0b405f5e1143459a5"
}

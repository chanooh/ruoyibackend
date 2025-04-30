import request from '@/utils/request'

// 查询工单图片列表
export function listImages(query) {
  return request({
    url: '/city/images/list',
    method: 'get',
    params: query
  })
}

// 查询工单图片详细
export function getImages(id) {
  return request({
    url: '/city/images/' + id,
    method: 'get'
  })
}

// 新增工单图片
export function addImages(data) {
  return request({
    url: '/city/images',
    method: 'post',
    data: data
  })
}

// 修改工单图片
export function updateImages(data) {
  return request({
    url: '/city/images',
    method: 'put',
    data: data
  })
}

// 删除工单图片
export function delImages(id) {
  return request({
    url: '/city/images/' + id,
    method: 'delete'
  })
}

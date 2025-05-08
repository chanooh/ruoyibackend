import request from '@/utils/request'

// 查询工单管理列表
export function listReports(query) {
  return request({
    url: '/city/reports/list',
    method: 'get',
    params: query
  })
}

// 查询工单管理详细
export function getReports(id) {
  return request({
    url: '/city/reports/' + id,
    method: 'get'
  })
}

// 新增工单管理
export function addReports(data) {
  return request({
    url: '/city/reports',
    method: 'post',
    data: data
  })
}

// 修改工单管理
export function updateReports(data) {
  return request({
    url: '/city/reports',
    method: 'put',
    data: data
  })
}

// 删除工单管理
export function delReports(id) {
  return request({
    url: '/city/reports/' + id,
    method: 'delete'
  })
}

export function assignReport(data) {
  return request({
    url: '/city/api/assign',
    method: 'put',
    data: data
  })
}